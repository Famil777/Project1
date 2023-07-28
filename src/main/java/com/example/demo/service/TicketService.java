package com.example.demo.service;

import com.example.demo.exceptions.HallNotFound;
import com.example.demo.exceptions.MovieNotFound;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface TicketService {
    void createTicket(Long hallId, Long movieId, LocalDateTime startTime) throws MovieNotFound, HallNotFound;

    void deleteTicket(Long sessionId);
}
