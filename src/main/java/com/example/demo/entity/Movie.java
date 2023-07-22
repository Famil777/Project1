package com.example.demo.entity;


import com.example.demo.entity.enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "movie")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Movie {
    @Id
    @SequenceGenerator(name = "movie_sequence" , sequenceName = "movie_sequence" , allocationSize = 1)
    @GeneratedValue(generator = "movie_sequence" , strategy = GenerationType.SEQUENCE)
    private Long movieId;
    private String name;
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "movie")
    private List<Session> session;
}
