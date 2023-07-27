package com.example.demo.dto;


import com.example.demo.entity.enums.Genre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private String name;
    private Integer duration;
    private Double rating;
    private List<String> genres;
}
