package com.formkio.formfio.exceptions;

public class InvalidJWT extends RuntimeException {
    public InvalidJWT(String message) {
        super(message);
    }
}
