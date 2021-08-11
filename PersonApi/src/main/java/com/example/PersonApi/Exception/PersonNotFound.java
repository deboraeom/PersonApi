package com.example.PersonApi.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PersonNotFound extends Exception {
    public PersonNotFound(Long id) {
        super(String.format("Person with ID %s not found!", id));
    }
}
