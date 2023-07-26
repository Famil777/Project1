package com.example.demo.entity;


import com.example.demo.entity.enums.Genre;
import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "movie_name" , nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private Double rating;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "movie")
    private List<Session> session;
}
