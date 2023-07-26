package com.example.demo.exceptions;

public class EmailAlreadyTaken extends Exception{
    public EmailAlreadyTaken(String message){
        super(message);
    }
}
