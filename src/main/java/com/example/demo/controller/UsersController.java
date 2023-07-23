package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Users;
import com.example.demo.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @PostMapping
    public void buyTicket(@RequestBody Users user){
        usersService.buyTicket(user);
    }
    
}
