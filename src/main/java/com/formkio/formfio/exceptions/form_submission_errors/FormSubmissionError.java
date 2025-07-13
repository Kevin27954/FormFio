package com.formkio.formfio.exceptions.form_submission_errors;

public abstract class FormSubmissionError extends RuntimeException {
    protected FormSubmissionError(String message) {
        super(message);
    }

    protected FormSubmissionError(String message, Throwable cause) {
        super(message, cause);
    }

    public String getMessage() {
        return super.getMessage();
    }
}

