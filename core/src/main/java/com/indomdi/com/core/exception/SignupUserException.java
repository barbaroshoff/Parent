package com.indomdi.com.core.exception;

import org.springframework.stereotype.Component;


public class SignupUserException extends Exception {

    private static final long serialVersionUID = -5098550615235554055L;

    public SignupUserException(Exception ex) {
        super(ex);
    }
}
