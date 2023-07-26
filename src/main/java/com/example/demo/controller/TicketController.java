package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.*;

import com.example.demo.exceptions.HallNotFound;
import com.example.demo.exceptions.MovieNotFound;
import com.example.demo.service.TicketService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/ticket")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/{hallId}/{movieId}")
    public void createTicket(@PathVariable("hallId") Long hallId,
                             @PathVariable("movieId") Long movieId,
                             LocalDateTime startTime) throws MovieNotFound, HallNotFound {

        ticketService.createTicket(hallId, movieId, startTime);

    }

    @DeleteMapping(path = "/{session-id}")
    public void deleteTicket(@PathVariable("session-id") Long sessionId){
        ticketService.deleteTicket(sessionId);
    }

}
