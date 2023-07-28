package com.example.demo.service.impl;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.Ticket;
import com.example.demo.entity.Users;
import com.example.demo.exceptions.EmailAlreadyTaken;
import com.example.demo.exceptions.TicketAlreadyTaken;
import com.example.demo.exceptions.TicketNotFound;
import com.example.demo.exceptions.UserNotFound;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.UsersService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final TicketRepository ticketRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        return usersRepository.findAll().stream().map(userMapper::usersToUserDto).toList();
    }

    public void createUser(Users user) throws EmailAlreadyTaken {
        Optional<Users> userByEmail = usersRepository.findByEmail(user.getEmail());

        if (userByEmail.isPresent()) {
            throw new EmailAlreadyTaken("Email already taken");
        }

        usersRepository.save(user);
    }

    @Transactional
    public void updateUser(Long userId, String name, String surname, String email, String password) throws UserNotFound, EmailAlreadyTaken {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new UserNotFound("user doesn't exist"));

        if (name != null && name.length() > 0 && name != user.getName()) {
            user.setName(name);
        }

        if (surname != null && surname.length() > 0 && surname != user.getSurname()) {
            user.setSurname(surname);
        }

        if (password != null && password.length() > 0 && password != user.getPassword()) {
            user.setPassword(password);
        }

        if (email != null && email.length() > 0 && email != user.getEmail()) {
            Optional<Users> userByEmail = usersRepository.findByEmail(email);
            if (userByEmail.isPresent()) {
                throw new EmailAlreadyTaken("Email already taken");
            }
            user.setEmail(email);
        }
    }

    public void deleteUser(Long userId) {
        usersRepository.deleteById(userId);
    }

    ////////////////////////////////////////
    // buying ticket
    @Transactional
    public void buyTicket(Long userId, Long ticketId) throws TicketAlreadyTaken, UserNotFound, TicketNotFound {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFound("Ticket doesnt exist"));

        if (ticket.getUsers() != null) {
            throw new TicketAlreadyTaken("Ticket is already taken ");
        }
        if (userId == null) {
            throw new UserNotFound("Sign in first");
        }

        Users user = usersRepository.findById(userId).orElseThrow(() -> new UserNotFound("Sign in first"));

        ticket.setUsers(user);
    }


    @Transactional
    public void deleteTicket(Long userId, Long ticketId) throws TicketNotFound, UserNotFound {

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFound("Ticket doesn't exist"));

        if (userId == null) {
            throw new UserNotFound("Sign in first");
        }

        ticket.setUsers(null);

    }
}
