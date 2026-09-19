package com.netflixclone.netflix_clone.repository;

import com.netflixclone.netflix_clone.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Page<Movie> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    @Query("SELECT DISTINCT m FROM Movie m JOIN m.genres g WHERE LOWER(g.name) = LOWER(:genreName)")
    Page<Movie> findByGenreName(@Param("genreName") String genreName, Pageable pageable);

    @Query("SELECT DISTINCT m FROM Movie m JOIN m.genres g " +
           "WHERE (:title IS NULL OR LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
           "AND (:genre IS NULL OR LOWER(g.name) = LOWER(:genre))")
    Page<Movie> findByFilters(@Param("title") String title, @Param("genre") String genre, Pageable pageable);

    boolean existsByTitleIgnoreCase(String title);
}
