package com.netflixclone.netflix_clone.controller;

import com.netflixclone.netflix_clone.dto.response.WatchlistResponse;
import com.netflixclone.netflix_clone.service.WatchlistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
@RequiredArgsConstructor
@Tag(name = "Watchlist", description = "Manage the authenticated user's watchlist")
@SecurityRequirement(name = "bearerAuth")
public class WatchlistController {

    private final WatchlistService watchlistService;

    @GetMapping
    @Operation(summary = "Get my watchlist")
    public ResponseEntity<List<WatchlistResponse>> getWatchlist(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(watchlistService.getWatchlist(userDetails.getUsername()));
    }

    @PostMapping("/{movieId}")
    @Operation(summary = "Add movie to watchlist")
    public ResponseEntity<WatchlistResponse> addToWatchlist(
            @PathVariable Long movieId,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(watchlistService.addToWatchlist(userDetails.getUsername(), movieId));
    }

    @DeleteMapping("/{movieId}")
    @Operation(summary = "Remove movie from watchlist")
    public ResponseEntity<Void> removeFromWatchlist(
            @PathVariable Long movieId,
            @AuthenticationPrincipal UserDetails userDetails) {
        watchlistService.removeFromWatchlist(userDetails.getUsername(), movieId);
        return ResponseEntity.noContent().build();
    }
}
