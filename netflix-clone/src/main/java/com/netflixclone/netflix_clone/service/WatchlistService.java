package com.netflixclone.netflix_clone.service;

import com.netflixclone.netflix_clone.dto.response.WatchlistResponse;
import com.netflixclone.netflix_clone.entity.Movie;
import com.netflixclone.netflix_clone.entity.User;
import com.netflixclone.netflix_clone.entity.Watchlist;
import com.netflixclone.netflix_clone.exception.DuplicateResourceException;
import com.netflixclone.netflix_clone.exception.ResourceNotFoundException;
import com.netflixclone.netflix_clone.repository.MovieRepository;
import com.netflixclone.netflix_clone.repository.UserRepository;
import com.netflixclone.netflix_clone.repository.WatchlistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public List<WatchlistResponse> getWatchlist(String email) {
        User user = getUserByEmail(email);
        return watchlistRepository.findByUserId(user.getId())
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public WatchlistResponse addToWatchlist(String email, Long movieId) {
        User user = getUserByEmail(email);
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));

        if (watchlistRepository.existsByUserIdAndMovieId(user.getId(), movieId)) {
            throw new DuplicateResourceException("Movie is already in your watchlist");
        }

        Watchlist entry = Watchlist.builder()
                .user(user)
                .movie(movie)
                .build();

        entry = watchlistRepository.save(entry);
        log.info("User {} added movie {} to watchlist", email, movieId);
        return toResponse(entry);
    }

    @Transactional
    public void removeFromWatchlist(String email, Long movieId) {
        User user = getUserByEmail(email);
        if (!watchlistRepository.existsByUserIdAndMovieId(user.getId(), movieId)) {
            throw new ResourceNotFoundException("Movie not found in your watchlist");
        }
        watchlistRepository.deleteByUserIdAndMovieId(user.getId(), movieId);
        log.info("User {} removed movie {} from watchlist", email, movieId);
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    private WatchlistResponse toResponse(Watchlist watchlist) {
        return WatchlistResponse.builder()
                .id(watchlist.getId())
                .movieId(watchlist.getMovie().getId())
                .movieTitle(watchlist.getMovie().getTitle())
                .posterUrl(watchlist.getMovie().getPosterUrl())
                .addedAt(watchlist.getAddedAt())
                .build();
    }
}
