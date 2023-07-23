package com.example.demo.service;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.Movie;
import com.example.demo.mapper.MovieMapper;
import com.example.demo.repository.MovieRepository;
import com.example.demo.specification.MovieSpecification;

import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream().map(movieMapper::movieToMovieDto).toList();
    }
    public List<MovieDto> containsName(String name) {

        Specification<Movie> specifications = Specification.where(MovieSpecification.containsName(name));

        return movieRepository.findAll(specifications).stream().map(movieMapper::movieToMovieDto).toList();
    }
}
