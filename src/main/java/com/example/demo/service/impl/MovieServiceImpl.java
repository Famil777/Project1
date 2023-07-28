package com.example.demo.service.impl;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.Movie;
import com.example.demo.entity.enums.Genre;
import com.example.demo.exceptions.MovieNotFound;
import com.example.demo.mapper.MovieMapper;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.MovieService;
import com.example.demo.specification.MovieSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;


    // getALlMovies
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream().map(movieMapper::movieToMovieDto).toList();
    }

    //gel all movies by name
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
    public void updateMovie(Long movieId, List<Genre> genres, String name) throws MovieNotFound {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFound("Movie doesn't exist"));

        if (!movie.getName().equals(name) && name != null && name.length() > 0) {
            movie.setName(name);
        }
        if (genres != null) {
            movie.setGenres(genres);
        }

    }


    //deleteMovie
    public void deleteMovie(Long movieId) throws MovieNotFound {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFound("Movie doesn't exist"));
        movieRepository.delete(movie);
    }

    public List<MovieDto> findByGenre(List<Genre> genres) {
        Specification<Movie> specifications = Specification.where(MovieSpecification.findByGenres(genres));
//        List<Movie> all = movieRepository.findAll(specifications);
        return movieRepository.findAll(specifications).stream().map(movieMapper::movieToMovieDto).toList();
    }

    public List<MovieDto> greaterThanRating(Double rating) {
        Specification<Movie> specifications = Specification.where(MovieSpecification.greaterThanRating(rating));
        return movieRepository.findAll(specifications).stream().map(movieMapper::movieToMovieDto).toList();
    }

    public List<MovieDto> findByGenreAndRating(List<Genre> genres, Double rating) {
        Specification<Movie> specifications = Specification.where(MovieSpecification.findByGenres(genres)
                .and(MovieSpecification.greaterThanRating(rating)));

        return movieRepository.findAll(specifications).stream().map(movieMapper::movieToMovieDto).toList();
    }
}
