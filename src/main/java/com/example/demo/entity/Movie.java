package com.example.demo.entity;


import com.example.demo.entity.enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie", uniqueConstraints = @UniqueConstraint(name = "unique_movie_constraint", columnNames = "movie_name"))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Movie {
    @Id
    @SequenceGenerator(name = "movie_sequence", sequenceName = "movie_sequence", allocationSize = 1)
    @GeneratedValue(generator = "movie_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "movie_name", nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private Double rating;

    @ElementCollection(targetClass = Genre.class)
    @JoinTable(name = "tb_genre", joinColumns = @JoinColumn(name = "movieId"))
    @Column(name = "genreId", nullable = false)
    @Enumerated
    private List<Genre> genres;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<Session> session;
}
