package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "session",
        uniqueConstraints = @UniqueConstraint(name = "hall_startTime_constraint", columnNames = {"start_time", "hall_id"}))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Session {
    @Id
    @SequenceGenerator(name = "session_sequence", sequenceName = "session_sequence", allocationSize = 1)
    @GeneratedValue(generator = "session_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_time" , nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "session",cascade = CascadeType.ALL)
    private List<Ticket> ticket;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @ManyToOne(optional = false) //cascade = CascadeType.ALL ,
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;
}
