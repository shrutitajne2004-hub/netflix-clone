package com.netflixclone.netflix_clone.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

import java.io.Serializable;

@Data
@Builder
public class MovieResponse implements Serializable {
    private Long id;
    private String title;
    private String description;
    private Integer releaseYear;
    private String posterUrl;
    private String videoUrl;
    private Double averageRating;
    private Set<String> genres;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
