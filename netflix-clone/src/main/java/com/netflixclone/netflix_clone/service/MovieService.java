package com.netflixclone.netflix_clone.service;

import com.netflixclone.netflix_clone.dto.request.MovieRequest;
import com.netflixclone.netflix_clone.dto.response.MovieResponse;
import com.netflixclone.netflix_clone.entity.Genre;
import com.netflixclone.netflix_clone.entity.Movie;
import com.netflixclone.netflix_clone.exception.ResourceNotFoundException;
import com.netflixclone.netflix_clone.repository.GenreRepository;
import com.netflixclone.netflix_clone.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    @Cacheable(value = "movies", key = "'all_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<MovieResponse> getAllMovies(Pageable pageable) {
        log.info("Fetching all movies from database (DB hit) - Page: {}", pageable.getPageNumber());
        return movieRepository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "movies", key = "'search_' + (#title != null ? #title : '') + '_' + (#genre != null ? #genre : '') + '_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<MovieResponse> searchMovies(String title, String genre, Pageable pageable) {
        log.info("Fetching searched movies from database (DB hit) - Title: {}, Genre: {}, Page: {}", title, genre, pageable.getPageNumber());
        if (StringUtils.hasText(title) && StringUtils.hasText(genre)) {
            return movieRepository.findByFilters(title, genre, pageable).map(this::toResponse);
        } else if (StringUtils.hasText(title)) {
            return movieRepository.findByTitleContainingIgnoreCase(title, pageable).map(this::toResponse);
        } else if (StringUtils.hasText(genre)) {
            return movieRepository.findByGenreName(genre, pageable).map(this::toResponse);
        }
        return movieRepository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "movie", key = "#id")
    public MovieResponse getMovieById(Long id) {
        log.info("Fetching movie from database (DB hit) - ID: {}", id);
        return movieRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
    }

    @Transactional
    @CacheEvict(value = "movies", allEntries = true)
    public MovieResponse createMovie(MovieRequest request) {
        Set<Genre> genres = resolveGenres(request.getGenres());
        Movie movie = Movie.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .releaseYear(request.getReleaseYear())
                .posterUrl(request.getPosterUrl())
                .videoUrl(request.getVideoUrl())
                .genres(genres)
                .build();
        movie = movieRepository.save(movie);
        log.info("Created movie: {} (id={})", movie.getTitle(), movie.getId());
        return toResponse(movie);
    }

    @Transactional
    @Caching(
        evict = { @CacheEvict(value = "movies", allEntries = true) },
        put = { @CachePut(value = "movie", key = "#id") }
    )
    public MovieResponse updateMovie(Long id, MovieRequest request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));

        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setPosterUrl(request.getPosterUrl());
        movie.setVideoUrl(request.getVideoUrl());
        movie.setGenres(resolveGenres(request.getGenres()));

        movie = movieRepository.save(movie);
        log.info("Updated movie: {} (id={})", movie.getTitle(), movie.getId());
        return toResponse(movie);
    }

    @Transactional
    @Caching(evict = {
        @CacheEvict(value = "movies", allEntries = true),
        @CacheEvict(value = "movie", key = "#id")
    })
    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new ResourceNotFoundException("Movie", "id", id);
        }
        movieRepository.deleteById(id);
        log.info("Deleted movie id={}", id);
    }

    // ---- Helpers ----

    private Set<Genre> resolveGenres(Set<String> genreNames) {
        return genreNames.stream()
                .map(name -> genreRepository.findByNameIgnoreCase(name)
                        .orElseGet(() -> genreRepository.save(
                                Genre.builder().name(name).build())))
                .collect(Collectors.toSet());
    }

    public MovieResponse toResponse(Movie movie) {
        Set<String> genreNames = movie.getGenres().stream()
                .map(Genre::getName)
                .collect(Collectors.toSet());
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseYear(movie.getReleaseYear())
                .posterUrl(movie.getPosterUrl())
                .videoUrl(movie.getVideoUrl())
                .averageRating(movie.getAverageRating())
                .genres(genreNames)
                .createdAt(movie.getCreatedAt())
                .updatedAt(movie.getUpdatedAt())
                .build();
    }
}
