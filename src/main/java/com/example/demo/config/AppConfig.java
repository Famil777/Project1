package com.example.demo.config;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.Genre;
import com.example.demo.entity.enums.Role;
import com.example.demo.repository.HallRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.SeatRepository;
import com.example.demo.repository.SessionRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Configuration
public class AppConfig {

    @Bean
    public CommandLineRunner commandLineRunner(UsersRepository usersRepository,
                                               MovieRepository movieRepository,
                                               TicketRepository ticketRepository,
                                               HallRepository hallRepository,
                                               SessionRepository sessionRepository,
                                               SeatRepository seatRepository) {
        return args -> {

            //movies
            Movie fnaf = Movie.builder()
                    .name("fnaf")
                    .duration(120)
                    .rating(5.5)
                    .genre(Genre.HORROR)
                    .build();

            Movie interstellar = Movie.builder()
                    .name("interstellar")
                    .duration(169)
                    .rating(7.8)
                    .genre(Genre.SCIENCE_FICTION)
                    .build();

            Movie oppenheimer = Movie.builder()
                    .name("Oppenheimer")
                    .duration(180)
                    .rating(3.0)
                    .genre(Genre.HISTORY)
                    .build();

            Movie inception = Movie.builder()
                    .name("Inception")
                    .duration(148)
                    .rating(7.7)
                    .genre(Genre.SCIENCE_FICTION)
                    .build();

            
            movieRepository.saveAll(List.of(inception,fnaf,interstellar,oppenheimer));

            //Halls
            Hall sectionA = Hall.builder().name("Section_A").capacity(30).build();
            Hall sectionB = Hall.builder().name("Section_B").capacity(30).build();
            Hall sectionC = Hall.builder().name("Section_C").capacity(30).build();
            hallRepository.saveAll(List.of(sectionA,sectionB,sectionC));

            //sessions
            Session inceptionSession = Session.builder()
            .startTime(LocalDateTime.of(2023, 10, 4, 12, 0, 0))
            .endTime(LocalDateTime.of(2023, 12, 4, 12, 0, 0).plusMinutes(inception.getDuration()))
            .hall(sectionC)
            .movie(inception)
            .build();

            Session fnafSession = Session.builder()
            .startTime(LocalDateTime.of(2023, 10, 4, 12, 0, 0))
            .endTime(LocalDateTime.of(2023, 10, 4, 12, 0, 0).plusMinutes(fnaf.getDuration()))
            .hall(sectionA)
            .movie(fnaf)
            .build();

            Session oppenheimerSession = Session.builder()
            .startTime(LocalDateTime.of(2023, 1, 4, 12, 0, 0))
            .endTime(LocalDateTime.of(2023, 1, 4, 12, 0, 0).plusMinutes(oppenheimer.getDuration()))
            .hall(sectionA)
            .movie(oppenheimer)
            .build();


            //seat and tickets
            IntStream.rangeClosed(1,sectionC.getCapacity()).forEach(a -> {
            Seat seat_add = Seat.builder().seatNumber(a).hall(sectionC).build();
            Ticket ticket = Ticket.builder().seat(seat_add).session(inceptionSession).build();
            ticketRepository.save(ticket);});//inception

            IntStream.rangeClosed(1,sectionC.getCapacity()).forEach(a -> {
                        Seat seat_add = Seat.builder().seatNumber(a).hall(sectionA).build();
                        Ticket ticket = Ticket.builder().seat(seat_add).session(fnafSession).build();
                        ticketRepository.save(ticket);});//fnaf

        };
    }
}
