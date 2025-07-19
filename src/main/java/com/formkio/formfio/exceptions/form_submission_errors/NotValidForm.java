package com.formkio.formfio.exceptions.form_submission_errors;

public class NotValidForm extends RuntimeException {
    public NotValidForm(String message) {
        super(message);
    }
}
