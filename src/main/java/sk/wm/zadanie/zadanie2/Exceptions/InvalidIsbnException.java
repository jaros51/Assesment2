package sk.wm.zadanie.zadanie2.Exceptions;

public class InvalidIsbnException extends RuntimeException{
    public InvalidIsbnException(String message) {
        super(message);
    }
}
