package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "seat")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Seat {
    @Id
    @SequenceGenerator(name = "seat_sequence" , sequenceName = "seat_sequence" , allocationSize = 1)
    @GeneratedValue(generator = "seat_sequence" , strategy = GenerationType.SEQUENCE)
    private Long seatId;
    private Integer searNumber;

    @OneToMany(mappedBy = "seat")
    private List<Ticket> ticket;


    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "hall_id" , referencedColumnName = "hallId")
    private Hall hall;
}
