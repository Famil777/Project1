package com.example.demo.service;



import org.springframework.stereotype.Service;

import com.example.demo.entity.Ticket;
import com.example.demo.entity.Users;
import com.example.demo.exceptions.TicketAlreadyTakenException;
import com.example.demo.exceptions.TicketNotFound;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UsersRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final TicketRepository ticketRepository;

    // buying ticket
    @Transactional
    public void buyTicket(Long userId , Long ticketId) throws TicketAlreadyTakenException, UserNotFoundException, TicketNotFound { 
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFound("Ticket doesnt exist"));
     
        if(ticket.getUsers() != null){throw new TicketAlreadyTakenException("Ticket is already taken ");}
        if(userId == null){throw new UserNotFoundException("Sign in first");} //usernotfound -- is it signed?

        Users user = usersRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException("Sign in first"));

        ticket.setUsers(user);
    }

    // delete ticket.Can l use this?.(RIP DRY)
    @Transactional
    public void deleteTicket(Long userId, Long ticketId) throws TicketNotFound, TicketAlreadyTakenException, UserNotFoundException {

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFound("Ticket doesnt exist"));
     
        
        if(userId == null){throw new UserNotFoundException("Sign in first");} //usernotfound -- is it signed?
        ticket.setUsers(null);

    }
    
}
