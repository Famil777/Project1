package com.example.demo.mapper;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDto movieToMovieDto(Movie movie);

    Movie movieDtoToMovie(MovieDto movieDto);
}
