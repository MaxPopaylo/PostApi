package api.TestAPI.utils.exceptions;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ValidationException extends ResponseStatusException {

    public ValidationException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
