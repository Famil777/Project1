package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Hall;
import com.example.demo.entity.Movie;
import com.example.demo.entity.Seat;
import com.example.demo.entity.Session;
import com.example.demo.entity.Ticket;
import com.example.demo.exceptions.HallNotFound;
import com.example.demo.exceptions.MovieNotFound;
import com.example.demo.repository.HallRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.SeatRepository;
import com.example.demo.repository.SessionRepository;
import com.example.demo.repository.TicketRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final MovieRepository movieRepository;
    private final HallRepository hallRepository;
    private final SessionRepository sessionRepository;
    private final SeatRepository seatRepository;


    @Transactional
    public void createTicket(Long hallId, Long movieId, LocalDateTime startTime) throws MovieNotFound, HallNotFound {

        Movie movie = movieRepository.findByMovieId(movieId).orElseThrow(() -> new MovieNotFound("Movie doesnt exist"));
        Hall hall = hallRepository.findByHallId(hallId).orElseThrow(() -> new HallNotFound("Hall doesnt exist"));

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
                    ticketRepository.save(ticket);
                } );

    }

    public void deleteTicket(Long sessionId) {
        sessionRepository.deleteById(sessionId);

    }

}
