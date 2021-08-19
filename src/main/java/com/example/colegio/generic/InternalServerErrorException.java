package co.edu.multilingua.erp.exception.generic;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InternalServerErrorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final HttpStatus errorCode = HttpStatus.INTERNAL_SERVER_ERROR;

    public InternalServerErrorException() {
        super("Internal server error");
    }

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}