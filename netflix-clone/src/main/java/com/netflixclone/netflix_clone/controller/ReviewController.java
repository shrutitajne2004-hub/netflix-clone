package com.netflixclone.netflix_clone.controller;

import com.netflixclone.netflix_clone.dto.request.ReviewRequest;
import com.netflixclone.netflix_clone.dto.response.ReviewResponse;
import com.netflixclone.netflix_clone.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies/{movieId}/reviews")
@RequiredArgsConstructor
@Tag(name = "Reviews", description = "Add and view movie reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    @Operation(summary = "Get all reviews for a movie")
    public ResponseEntity<List<ReviewResponse>> getReviews(@PathVariable Long movieId) {
        return ResponseEntity.ok(reviewService.getReviewsByMovie(movieId));
    }

    @PostMapping
    @Operation(summary = "Add a review to a movie", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ReviewResponse> addReview(
            @PathVariable Long movieId,
            @Valid @RequestBody ReviewRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewService.addReview(userDetails.getUsername(), movieId, request));
    }
}
