package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public void buyTicket(Users user) {
    }
    
}
