package com.example.demo.controller;


import com.example.demo.dto.UserDto;
import com.example.demo.entity.Users;
import com.example.demo.exceptions.EmailAlreadyTaken;
import org.springframework.web.bind.annotation.*;


import com.example.demo.exceptions.TicketAlreadyTaken;
import com.example.demo.exceptions.TicketNotFound;
import com.example.demo.exceptions.UserNotFound;
import com.example.demo.service.UsersService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping
    public List<UserDto> getALlUsers() {
        return usersService.getAllUsers();
    }


    @PostMapping
    public void createUser(@RequestBody Users user) throws EmailAlreadyTaken {
        usersService.createUser(user);
    }

    @PutMapping("/{user_id}")
    public void updateUser(@PathVariable("user_id") Long userId,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String surname,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String password) throws UserNotFound, EmailAlreadyTaken {
        usersService.updateUser(userId, name, surname, email, password);
    }

    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable("user_id") Long userId) {
        usersService.deleteUser(userId);
    }


    @PostMapping(path = "/{userId}/{ticketId}")
    public void buyTicket(@PathVariable("userId") Long userId,
                          @PathVariable("ticketId") Long ticketId) throws TicketAlreadyTaken, UserNotFound, TicketNotFound {
        usersService.buyTicket(userId, ticketId);
    }

    @DeleteMapping(path = "/{userId}/{ticketId}")
    public void deleteTicket(@PathVariable("userId") Long userId,
                             @PathVariable("ticketId") Long ticketId) throws TicketNotFound, UserNotFound {

        usersService.deleteTicket(userId, ticketId);

    }

}
