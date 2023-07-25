package com.example.demo.service;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.Movie;
import com.example.demo.entity.enums.Genre;
import com.example.demo.exceptions.MovieNotFound;
import com.example.demo.mapper.MovieMapper;
import com.example.demo.repository.MovieRepository;
import com.example.demo.specification.MovieSpecification;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;


    // getALlMovies
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream().map(movieMapper::movieToMovieDto).toList();
    }
    public List<MovieDto> containsName(String name) {

        Specification<Movie> specifications = Specification.where(MovieSpecification.containsName(name));

        return movieRepository.findAll(specifications).stream().map(movieMapper::movieToMovieDto).toList();
    }

    // Add Movie
    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    // updateMovie
    @Transactional
    public void updateMovie(Long movieId, LocalDate date, Genre genre, String name) throws MovieNotFound {

        Movie movie = movieRepository.findByMovieId(movieId).orElseThrow(() -> new MovieNotFound("Movie doesnt exist") );

        System.out.println("before " +movie.getName());
        System.out.println(name != null);

        if(!movie.getName().equals(name) && name != null && name.length()>0){movie.setName(name);}
        if(date != null){movie.setReleaseDate(date);}
        if(genre != null){movie.setGenre(genre);}

        System.out.println("after " + movie.getName());

    }


    //deleteMovie
    public void deleteMovie(Long movieId) throws MovieNotFound {

        Movie movie = movieRepository.findByMovieId(movieId).orElseThrow(() -> new MovieNotFound("Movie doesnt exist") );
        movieRepository.delete(movie);

    }


 
}
