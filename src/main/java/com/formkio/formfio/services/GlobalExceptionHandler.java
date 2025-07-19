package com.formkio.formfio.services;

import com.formkio.formfio.exceptions.InternalError;
import com.formkio.formfio.exceptions.MissingValueError;
import com.formkio.formfio.exceptions.NotUniqueUUIDError;
import com.formkio.formfio.exceptions.form_submission_errors.FormSubmissionInternalError;
import com.formkio.formfio.exceptions.form_submission_errors.NotValidForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FormSubmissionInternalError.class)
    public ResponseEntity<Object> handleFormSubmissionInternalError(FormSubmissionInternalError e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(MissingValueError.class)
    public ResponseEntity<Object> handleMissingValueError(MissingValueError e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(NotUniqueUUIDError.class)
    public ResponseEntity<Object> handleNotUniqueUUID(NotUniqueUUIDError e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(NotValidForm.class)
    public ResponseEntity<Object> handleNotValidForm(NotUniqueUUIDError e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InternalError.class)
    public ResponseEntity<Object> handleInternalError(NotUniqueUUIDError e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
