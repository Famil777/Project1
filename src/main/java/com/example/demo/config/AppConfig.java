package com.example.demo.config;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.Genre;
import com.example.demo.entity.enums.Role;
import com.example.demo.repository.HallRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public CommandLineRunner commandLineRunner(UsersRepository usersRepository,
                                               MovieRepository movieRepository,
                                               TicketRepository ticketRepository,
                                               HallRepository hallRepository) {
        return args -> {


            //movies
            Movie fnaf = Movie.builder()
                    .name("fnaf")
                    .duration(120)
                    .rating(5.5)
                    .releaseDate(LocalDate.of(2023, 10, 4))
                    .genre(Genre.HORROR)
                    .build();

            Movie interstellar = Movie.builder()
                    .name("interstellar")
                    .duration(169)
                    .rating(7.8)
                    .releaseDate(LocalDate.of(2025, 01, 24))
                    .genre(Genre.SCIENCE_FICTION)
                    .build();

            Movie oppenheimer = Movie.builder()
                    .name("Oppenheimer")
                    .duration(180)
                    .rating(3.0)
                    .releaseDate(LocalDate.of(2024, 4, 15))
                    .genre(Genre.HISTORY)
                    .build();

            Movie inception = Movie.builder()
                    .name("Inception")
                    .duration(148)
                    .rating(7.7)
                    .releaseDate(LocalDate.of(2024, 7, 28))
                    .genre(Genre.SCIENCE_FICTION)
                    .build();




            //Halls
            Hall sectionA = Hall.builder().name("Section_A").capacity(30).build();
            Hall sectionB = Hall.builder().name("Section_B").capacity(30).build();
            Hall sectionC = Hall.builder().name("Section_C").capacity(30).build();



            //Seats
            Seat fnafSeatA1 = Seat.builder().seatNumber(1).hall(sectionA).build();
            Seat fnafSeatA11 = Seat.builder().seatNumber(11).hall(sectionA).build();
            Seat fnafSeatB15 = Seat.builder().seatNumber(15).hall(sectionB).build();
            Seat inceptionSeatA15 = Seat.builder().seatNumber(15).hall(sectionA).build();

            //Sessions
            Session fnafSessionA1 = Session.builder()
                    .startTime(LocalDateTime.of(2023, 10, 4, 12, 0, 0))
                    .endTime(LocalDateTime.of(2023, 10, 4, 12, 0, 0).plusMinutes(fnaf.getDuration()))
                    .movie(fnaf)
                    .hall(sectionA)
                    .build();
            Session fnafSessionA11 = Session.builder()
                    .startTime(LocalDateTime.of(2023, 10, 11, 20, 0, 0))
                    .endTime(LocalDateTime.of(2023, 10, 4, 12, 0, 0).plusMinutes(fnaf.getDuration()))
                    .movie(fnaf)
                    .hall(sectionA)
                    .build();
            Session fnafSessionB15 = Session.builder()
                    .startTime(LocalDateTime.of(2023, 10, 11, 20, 0, 0))
                    .endTime(LocalDateTime.of(2023, 10, 4, 12, 0, 0).plusMinutes(fnaf.getDuration()))
                    .movie(fnaf)
                    .hall(sectionB)
                    .build();


            Session inceptionSessionA15 = Session.builder()
                    .startTime(LocalDateTime.of(2025, 6, 4, 20, 0, 0))
                    .endTime(LocalDateTime.of(2025, 6, 4, 20, 0, 0).plusMinutes(inception.getDuration()))
                    .movie(inception)
                    .hall(sectionA)
                    .build();


            //users
            Users tom = Users.builder().name("Tom")
                    .surname("Miller")
                    .email("tomMiller@gmail.com")
                    .password("asdfghwe")
                    .role(Role.USER)
                    .build();

            Users william = Users.builder().name("William")
                    .surname("Afton")
                    .email("williamAfton@gmail.com")
                    .password("biteOf1983")
                    .role(Role.USER)
                    .build();

            Users henry = Users.builder().name("Henry")
                    .surname("Emily")
                    .email("henryEmily@gmail.com")
                    .password("letmesaveyounow")
                    .role(Role.USER)
                    .build();

            Users michael = Users.builder().name("Michael")
                    .surname("Afton")
                    .email("michaelAftony@gmail.com")
                    .password("lwillputyoubacktogether")
                    .role(Role.USER)
                    .build();




            //tickets
            Ticket fnafTicketA1 = Ticket.builder().session(fnafSessionA1).seat(fnafSeatA1).users(william).build();
            Ticket fnafTicketA11 = Ticket.builder().session(fnafSessionA11).seat(fnafSeatA11).users(william).build();
            Ticket fnafTicketB15 = Ticket.builder().session(fnafSessionB15).seat(fnafSeatB15).users(henry).build();
            Ticket inceptionTicketA15 = Ticket.builder().session(inceptionSessionA15).users(null).seat(inceptionSeatA15).build();

            movieRepository.saveAll(List.of(fnaf, oppenheimer, inception, interstellar));
            ticketRepository.saveAll(List.of(fnafTicketA1, fnafTicketA11, fnafTicketB15, inceptionTicketA15));
            usersRepository.saveAll(List.of(william, henry, michael, tom));
            hallRepository.saveAll(List.of(sectionA , sectionB , sectionC));

        };
    }
}
