package hu.nye.docassist.exception;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorString = ex.getConstraintViolations()
                .stream()
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .collect(Collectors.joining(", "));
        // Log the detailed message along with the exception
        logger.error("Validation error: {} - Exception: {}", errorString, ex.toString());
        return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<String> handleTransactionSystemException(TransactionSystemException ex) {
        Throwable rootCause = ex.getRootCause();
        if (rootCause instanceof ConstraintViolationException) {
            return handleConstraintViolationException((ConstraintViolationException) rootCause);
        }

        logger.error("Transaction system error: ", ex);
        return new ResponseEntity<>("Transaction system error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        logger.error("Internal server error: ", ex);
        return new ResponseEntity<>("Internal server error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}