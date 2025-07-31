package com.formkio.formfio.exceptions;

public class LimitPassedError extends RuntimeException {
    public LimitPassedError(String message) {
        super(message);
    }
}
