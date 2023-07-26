package com.example.demo.controller;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.Movie;
import com.example.demo.entity.enums.Genre;
import com.example.demo.exceptions.MovieNotFound;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping(path = "/{name}")
    public List<MovieDto> containsName(@PathVariable(name = "name") String name) {
        return movieService.containsName(name);
    }

    @GetMapping("/genre={genre}")
    public List<MovieDto> findByGenre(@PathVariable("genre") Genre genre){
        return movieService.findByGenre(genre);
    }

    @GetMapping("/rating>{rating}")
    public List<MovieDto> greaterThanRating(@PathVariable("rating") Double rating){
        return movieService.greaterThanRating(rating);
    }

    @GetMapping("/genre={genre}/rating>{rating}")
    public List<MovieDto> findByGenreAndRating(@PathVariable("genre") Genre genre,
                                               @PathVariable("rating") Double rating){
        return movieService.findByGenreAndRating(genre,rating);
    }


    @PostMapping(path = "/add-movie")
    public void addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
    }

    @PutMapping(path = "/{movie-id}")
    public void updateMovie(@PathVariable("movie-id") Long movieId,
                            @RequestParam(required = false) LocalDate date,
                            @RequestParam(required = false) Genre genre,
                            @RequestParam(required = false) String name) throws MovieNotFound {

        movieService.updateMovie(movieId, date, genre, name);

    }


    @DeleteMapping(path = "/{movie-id}")
    public void deleteMovie(@PathVariable("movie-id") Long movieId) throws MovieNotFound {
        movieService.deleteMovie(movieId);
    }

}
