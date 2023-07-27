package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ticket")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Ticket {
    @Id
    @SequenceGenerator(name = "ticket_sequence", sequenceName = "ticket_sequence", allocationSize = 1)
    @GeneratedValue(generator = "ticket_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "ticket_id")
    private Long ticketId;

    @ManyToOne //(cascade and fetch)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY , optional = false) //cascade = CascadeType.ALL
    @JoinColumn(name = "session_id_fk", referencedColumnName = "session_id")
    private Session session;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY , optional = false) 
    @JoinColumn(name = "seat_id_fk", referencedColumnName = "seat_id")
    private Seat seat;
}
