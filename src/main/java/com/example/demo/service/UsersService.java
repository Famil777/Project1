package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.Users;
import com.example.demo.exceptions.EmailAlreadyTaken;
import com.example.demo.exceptions.TicketAlreadyTaken;
import com.example.demo.exceptions.TicketNotFound;
import com.example.demo.exceptions.UserNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {
    List<UserDto> getAllUsers();

    void createUser(Users user) throws EmailAlreadyTaken;

    void updateUser(Long userId,
                    String name,
                    String surname,
                    String email,
                    String password) throws UserNotFound, EmailAlreadyTaken;

    void deleteUser(Long userId);

    void buyTicket(Long userId, Long ticketId) throws TicketAlreadyTaken, UserNotFound, TicketNotFound;

    void deleteTicket(Long userId, Long ticketId) throws TicketNotFound, UserNotFound;
}
