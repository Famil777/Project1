package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "session",
        uniqueConstraints = @UniqueConstraint(name = "hall_startTime_constraint", columnNames = {"start_time", "hall_id_fk"}))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Session {
    @Id
    @SequenceGenerator(name = "session_sequence", sequenceName = "session_sequence", allocationSize = 1)
    @GeneratedValue(generator = "session_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "session_id")
    private Long sessionId;

    @Column(name = "start_time" , nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "session")
    private List<Ticket> ticket;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "movie_id_fk", referencedColumnName = "movie_id")
    private Movie movie;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY , optional = false) 
    @JoinColumn(name = "hall_id_fk", referencedColumnName = "hall_id")
    private Hall hall;
}
