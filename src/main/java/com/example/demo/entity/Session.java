package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "session")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Session {
    @Id
    @SequenceGenerator(name = "session_sequence" , sequenceName = "session_sequence" , allocationSize = 1)
    @GeneratedValue(generator = "session_sequence" , strategy = GenerationType.SEQUENCE)
    private Long sessionId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "session")
    private List<Ticket> ticket;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "movie_id" , referencedColumnName = "movieId")
    private Movie movie;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "hall_id" , referencedColumnName = "hallId")
    private Hall hall;
}
