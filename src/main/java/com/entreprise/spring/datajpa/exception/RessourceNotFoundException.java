package com.entreprise.spring.datajpa.exception;

public class RessourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public RessourceNotFoundException(String msg) {
        super(msg);
    }
}
