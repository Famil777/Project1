package com.example.demo.repository;


import com.example.demo.entity.Movie;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    @Query("Select m from Movie m where m.movieId = :movieId")
    Optional<Movie> findByMovieId(@Param("movieId") Long movieId);


}
