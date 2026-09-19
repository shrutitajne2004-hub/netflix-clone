package com.netflixclone.netflix_clone.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
public class MovieRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Release year is required")
    @Min(value = 1888, message = "Release year must be 1888 or later")
    @Max(value = 2100, message = "Release year seems invalid")
    private Integer releaseYear;

    private String posterUrl;

    private String videoUrl;

    @NotEmpty(message = "At least one genre is required")
    private Set<String> genres;
}
