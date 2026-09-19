package com.netflixclone.netflix_clone.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WatchlistResponse {
    private Long id;
    private Long movieId;
    private String movieTitle;
    private String posterUrl;
    private LocalDateTime addedAt;
}
