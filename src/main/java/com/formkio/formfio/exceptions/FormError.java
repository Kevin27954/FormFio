package com.formkio.formfio.exceptions;

public abstract class FormError extends RuntimeException {
    public FormError(String message) {
        super(message);
    }
}
