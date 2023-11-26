package com.techhub.crm.exception;

public class ContactExistException extends RuntimeException {

    public ContactExistException() {
        super();
    }

    public ContactExistException(String message) {
        super(message);
    }

    public ContactExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactExistException(Throwable cause) {
        super(cause);
    }
}
