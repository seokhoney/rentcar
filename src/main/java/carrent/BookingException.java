package carrent;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "No Available stock!")
public class BookingException extends RuntimeException {
    public BookingException(String message) {
        super(message);
    }
}