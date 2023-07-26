package com.example.demo.controller;


import org.springframework.web.bind.annotation.*;


import com.example.demo.exceptions.TicketAlreadyTakenException;
import com.example.demo.exceptions.TicketNotFound;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @PostMapping(path = "/{userId}/{ticketId}")
    public void buyTicket(@PathVariable("userId") Long userId,
                          @PathVariable("ticketId") Long ticketId) throws TicketAlreadyTakenException, UserNotFoundException, TicketNotFound {
        usersService.buyTicket(userId, ticketId);
    }

    @DeleteMapping(path = "/{userId}/{ticketId}")
    public void deleteTicket(@PathVariable("userId") Long userId,
                             @PathVariable("ticketId") Long ticketId) throws TicketNotFound, TicketAlreadyTakenException, UserNotFoundException {

        usersService.deleteTicket(userId, ticketId);

    }

}
