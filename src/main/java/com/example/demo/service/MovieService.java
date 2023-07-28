package com.example.demo.service;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.Movie;
import com.example.demo.entity.enums.Genre;
import com.example.demo.exceptions.MovieNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    List<MovieDto> getAllMovies();

    List<MovieDto> containsName(String name);

    void addMovie(Movie movie);

    void updateMovie(Long movieId, List<Genre> genres, String name) throws MovieNotFound;

    void deleteMovie(Long movieId) throws MovieNotFound;

    List<MovieDto> findByGenre(List<Genre> genres);

    List<MovieDto> greaterThanRating(Double rating);

    List<MovieDto> findByGenreAndRating(List<Genre> genres, Double rating);
}
