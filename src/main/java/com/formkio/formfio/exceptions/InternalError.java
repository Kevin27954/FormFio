package com.formkio.formfio.exceptions;

public class InternalError extends FormError {
    public InternalError() {
        super("Something went wrong. Please try again later");
    }

    public InternalError(String message) {
        super(message);
    }
}
