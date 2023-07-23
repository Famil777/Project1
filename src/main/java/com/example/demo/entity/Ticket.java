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
    @SequenceGenerator(name = "ticket_sequence" , sequenceName = "ticket_sequence" , allocationSize = 1)
    @GeneratedValue(generator = "ticket_sequence" , strategy = GenerationType.SEQUENCE)
    private Long ticketId;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , referencedColumnName = "userId")
    private Users users;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "session_id" , referencedColumnName = "sessionId")
    private Session session;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "seat_id" , referencedColumnName = "seatId")
    private Seat seat;
}
