package sk.wm.zadanie.zadanie2.Handler;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sk.wm.zadanie.zadanie2.Exceptions.InvalidIsbnException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<String> handleJsonMappingException(JsonMappingException e) {
        if (e.getCause() instanceof IllegalArgumentException) {
            return ResponseEntity.badRequest().body(e.getCause().getMessage());
        }
        return ResponseEntity.badRequest().body("Invalid ISBN format");
    }

    @ExceptionHandler(InvalidIsbnException.class)
    public ResponseEntity<String> handleIllegalArgumentException(InvalidIsbnException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}