package hu.nye.docassist.controller;

import hu.nye.docassist.exception.ClientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionHandlerController {
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found in the db.")
    @ExceptionHandler(ClientNotFoundException.class)
    public void handleUserNotFoundException() {

    }

}
