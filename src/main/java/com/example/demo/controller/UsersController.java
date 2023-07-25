package com.example.demo.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.exceptions.TicketAlreadyTakenException;
import com.example.demo.exceptions.TicketNotFound;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @PostMapping(path = "{userId}/{ticketId}")
    public void buyTicket(@PathVariable("userId") Long userId ,@PathVariable("ticketId") Long ticketId) throws TicketAlreadyTakenException, UserNotFoundException, TicketNotFound{ //void
      usersService.buyTicket(userId , ticketId);
    }
    
}
