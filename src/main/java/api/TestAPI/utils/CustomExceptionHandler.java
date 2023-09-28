package api.TestAPI.utils;

import api.TestAPI.utils.exceptions.InvalidDateException;
import api.TestAPI.utils.exceptions.UserNotFoundException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(ValidationException e) {
        log.error(e.getMessage(), e);
        return new ResponseError(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handle(UserNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseError(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(InvalidDateException e) {
        log.error(e.getMessage(), e);
        return new ResponseError(HttpStatus.BAD_REQUEST, e.getMessage());
    }

}
