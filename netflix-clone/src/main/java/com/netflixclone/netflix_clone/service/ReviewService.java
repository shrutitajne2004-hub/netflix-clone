package com.netflixclone.netflix_clone.service;

import com.netflixclone.netflix_clone.dto.request.ReviewRequest;
import com.netflixclone.netflix_clone.dto.response.ReviewResponse;
import com.netflixclone.netflix_clone.entity.Movie;
import com.netflixclone.netflix_clone.entity.Review;
import com.netflixclone.netflix_clone.entity.User;
import com.netflixclone.netflix_clone.exception.DuplicateResourceException;
import com.netflixclone.netflix_clone.exception.ResourceNotFoundException;
import com.netflixclone.netflix_clone.repository.MovieRepository;
import com.netflixclone.netflix_clone.repository.ReviewRepository;
import com.netflixclone.netflix_clone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviewsByMovie(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new ResourceNotFoundException("Movie", "id", movieId);
        }
        return reviewRepository.findByMovieId(movieId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReviewResponse addReview(String email, Long movieId, ReviewRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));

        if (reviewRepository.existsByUserIdAndMovieId(user.getId(), movieId)) {
            throw new DuplicateResourceException("You have already reviewed this movie");
        }

        Review review = Review.builder()
                .user(user)
                .movie(movie)
                .rating(request.getRating())
                .comment(request.getComment())
                .build();

        review = reviewRepository.save(review);

        // Update movie's average rating
        Double avg = reviewRepository.calculateAverageRatingByMovieId(movieId);
        movie.setAverageRating(avg != null ? Math.round(avg * 10.0) / 10.0 : null);
        movieRepository.save(movie);

        log.info("User {} reviewed movie {} with rating {}", email, movieId, request.getRating());
        return toResponse(review);
    }

    private ReviewResponse toResponse(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .movieId(review.getMovie().getId())
                .username(review.getUser().getUsername())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
