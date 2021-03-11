package com.indomdi.core.exception;

public class SignupUserException extends Exception {
    private static final long serialVersionUID = -5098550615235554055L;

    public SignupUserException(Exception ex) {
        super(ex);
    }
}
