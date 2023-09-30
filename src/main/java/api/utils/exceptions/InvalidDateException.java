package api.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class InvalidDateException extends ResponseStatusException {
    public InvalidDateException() {
        super(HttpStatus.BAD_REQUEST, "Invalid date");
    }
}
