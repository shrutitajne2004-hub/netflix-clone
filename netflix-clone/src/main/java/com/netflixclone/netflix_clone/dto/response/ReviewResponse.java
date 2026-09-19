package com.netflixclone.netflix_clone.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReviewResponse {
    private Long id;
    private Long movieId;
    private String username;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
}
