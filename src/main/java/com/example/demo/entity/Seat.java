package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "seat")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Seat {
    @Id
    @SequenceGenerator(name = "seat_sequence", sequenceName = "seat_sequence", allocationSize = 1)
    @GeneratedValue(generator = "seat_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @OneToMany(mappedBy = "seat")
    @JsonIgnore
    private List<Ticket> ticket;


    @ManyToOne(optional = false)
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;
}
