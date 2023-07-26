package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "hall")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Hall {
    @Id
    @SequenceGenerator(name = "hall_sequence", sequenceName = "hall_sequence", allocationSize = 1)
    @GeneratedValue(generator = "hall_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "hall_id")
    private Long hallId;

    @Column(name = "hall_name" , nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer capacity;

    @OneToMany(mappedBy = "hall")
    private List<Session> session;

    @OneToMany(mappedBy = "hall")
    private List<Seat> seat;
}
