package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exceptions.HallNotFound;
import com.example.demo.exceptions.MovieNotFound;
import com.example.demo.repository.*;
import com.example.demo.service.TicketService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.IntStream;


@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final MovieRepository movieRepository;
    private final HallRepository hallRepository;
    private final SessionRepository sessionRepository;
    private final SeatRepository seatRepository;


    @Transactional
    public void createTicket(Long hallId, Long movieId, LocalDateTime startTime) throws MovieNotFound, HallNotFound {

        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFound("Movie doesn't exist"));
        Hall hall = hallRepository.findById(hallId).orElseThrow(() -> new HallNotFound("Hall doesn't exist"));

        // create and save session
        Session newSession = Session.builder()
                .startTime(startTime)
                .endTime(startTime.plusMinutes(movie.getDuration()))
                .hall(hall).movie(movie)
                .build();

        sessionRepository.save(newSession);

        // create and save seats
        IntStream.rangeClosed(1, hall.getCapacity()).forEach(seat -> {
            Seat seat_add = Seat.builder().seatNumber(seat).hall(hall).build();
            seatRepository.save(seat_add);
            Ticket ticket = Ticket.builder().seat(seat_add).session(newSession).build();
            ticketRepository.save(ticket);});
    }

    //delete ticket
    public void deleteTicket(Long sessionId) {
        sessionRepository.deleteById(sessionId);
    }
}
