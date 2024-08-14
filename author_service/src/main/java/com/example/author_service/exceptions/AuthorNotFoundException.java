package com.example.author_service.exceptions;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(String s){
        super(s);
    }

}
