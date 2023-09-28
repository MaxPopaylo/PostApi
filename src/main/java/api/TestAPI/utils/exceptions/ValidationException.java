package api.TestAPI.utils.exceptions;

import api.TestAPI.utils.ResponseError;
import org.springframework.http.HttpStatus;

public class ValidationException extends ResponseError {
    public ValidationException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
