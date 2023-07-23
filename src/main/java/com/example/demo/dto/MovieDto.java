package com.example.demo.dto;


import com.example.demo.entity.enums.Genre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private String name;
    private LocalDate releaseDate;
    private Integer duration;
//    private double rating;
    @Enumerated(EnumType.STRING)
    private Genre genre;


}
