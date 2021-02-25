package com.indomdi.core.exception;

public class RegisterUserException extends Exception {
    private static final long serialVersionUID = -5098550615235554055L;

    public RegisterUserException(String error) {
        super(error);
    }

    public RegisterUserException(Exception ex) {
        super(ex);
    }
}
