package com.example.demo.exceptions;

public class TicketAlreadyTakenException extends Exception {

    public TicketAlreadyTakenException(String message){
        super(message);
    }
    
}
