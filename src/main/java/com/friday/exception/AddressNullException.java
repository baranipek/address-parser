package com.friday.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class AddressNullException extends Exception{

    private static final long serialVersionUID = 8234886813262412485L;

    public AddressNullException(String message) {super(message);}

}
