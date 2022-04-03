package com.udacity.jdnd.course3.critter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Schedule not found")
public class ScheduleNotFoundException extends RuntimeException{
    public ScheduleNotFoundException() {
    }

    public ScheduleNotFoundException(String message) {
        super(message);
    }
}
