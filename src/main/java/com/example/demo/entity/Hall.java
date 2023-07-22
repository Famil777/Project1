package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "hall")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Hall {
    @Id
    @SequenceGenerator(name = "hall_sequence" , sequenceName = "hall_sequence" , allocationSize = 1)
    @GeneratedValue(generator = "hall_sequence" , strategy = GenerationType.SEQUENCE)
    private Long hallId;
    private String name;
    private Integer capacity;

    @OneToMany(mappedBy = "hall")
    private List<Session> session;

    @OneToMany(mappedBy = "hall")
    private List<Seat> seat;

}
