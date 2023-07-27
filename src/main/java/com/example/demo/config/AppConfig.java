package com.example.demo.config;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.Genre;
import com.example.demo.entity.enums.Role;
import com.example.demo.repository.HallRepository;
import com.example.demo.repository.MovieRepository;
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
                                               SessionRepository sessionRepository,
                                               HallRepository hallRepository) {
        return args -> {

            //users
            Users michael = Users.builder()
                                 .name("Michael")
                                 .surname("Afton")
                                 .email("michaelAfton@gmail.com")
                                 .password("BiteOf1983")
                                 .role(Role.USER)
                                 .build();

            Users william = Users.builder()
                    .name("William")
                    .surname("Afton")
                    .email("williamAfton@gmail.com")
                    .password("purpleGuy")
                    .role(Role.USER)
                    .build();

            Users henry = Users.builder()
                    .name("Henry")
                    .surname("Emily")
                    .email("henryEmily@gmail.com")
                    .password("mimic1")
                    .role(Role.USER)
                    .build();

            Users jeremy = Users.builder()
                    .name("Jeremy")
                    .surname("Mu")
                    .email("jeremyMu@gmail.com")
                    .password("1234")
                    .role(Role.USER)
                    .build();

            usersRepository.saveAll(List.of(michael,william,henry,jeremy));

            //movies
            Movie fnaf = Movie.builder()
                    .name("fnaf")
                    .duration(120)
                    .rating(5.5)
                    .genres(List.of(Genre.ACTION,Genre.HORROR))
                    .build();

            Movie interstellar = Movie.builder()
                    .name("interstellar")
                    .duration(169)
                    .rating(7.8)
                    .genres(List.of(Genre.SCIENCE_FICTION,Genre.ACTION))
                    .build();

            Movie oppenheimer = Movie.builder()
                    .name("Oppenheimer")
                    .duration(180)
                    .rating(3.0)
                    .genres(List.of(Genre.CRIME,Genre.HISTORY))
                    .build();

            Movie inception = Movie.builder()
                    .name("Inception")
                    .duration(148)
                    .rating(7.7)
                    .genres(List.of(Genre.SCIENCE_FICTION))
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

            sessionRepository.saveAll(List.of(inceptionSession,fnafSession));


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
