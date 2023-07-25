package com.example.demo.controller;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.Movie;
import com.example.demo.entity.enums.Genre;
import com.example.demo.exceptions.MovieNotFound;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping(path = "/home")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieDto> getAllMovies(){
        return  movieService.getAllMovies();
    }

    @GetMapping(path = "/{name}")
    public List<MovieDto> containsName(@PathVariable(name = "name") String name){
        return movieService.containsName(name);
    }

    @PostMapping(path = "/addMovie")
    public void addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
    }

    @PutMapping(path = "/{movieId}")
    public void updateMovie(@PathVariable("movieId") Long movieId,
                            @RequestParam(required = false) LocalDate date , 
                            @RequestParam(required = false) Genre genre ,
                            @RequestParam(required = false) String name) throws MovieNotFound{

                                movieService.updateMovie(movieId , date , genre , name);



    }

}
