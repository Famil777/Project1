package com.example.demo.repository;

import com.example.demo.entity.Ticket;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("Select t from Ticket t where t.ticketId = :ticketId")
    Optional<Ticket> findByTicketId(@Param("ticketId") Long ticketId);

}
