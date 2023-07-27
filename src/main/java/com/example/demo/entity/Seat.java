package com.example.demo.entity;

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
    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "seat_number" , nullable = false)
    private Integer seatNumber;

    @OneToMany(mappedBy = "seat")
    private List<Ticket> ticket;


    @ManyToOne( optional = false) //cascade = CascadeType.ALL,
    @JoinColumn(name = "hall_id_fk", referencedColumnName = "hall_id")
    private Hall hall;
}
