package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "seat" , uniqueConstraints = @UniqueConstraint(name = "unique_seat_constraint" , columnNames = "seatNumber"))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Seat {
    @Id
    @SequenceGenerator(name = "seat_sequence" , sequenceName = "seat_sequence" , allocationSize = 1)
    @GeneratedValue(generator = "seat_sequence" , strategy = GenerationType.SEQUENCE)
    private Long seatId;
    private Integer seatNumber;

    @OneToMany(mappedBy = "seat")
    private List<Ticket> ticket;


    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "hall_id" , referencedColumnName = "hallId")
    private Hall hall;
}
