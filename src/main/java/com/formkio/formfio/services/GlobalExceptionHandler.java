package com.formkio.formfio.services;

import com.formkio.formfio.exceptions.*;
import com.formkio.formfio.exceptions.InternalError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FormSubmissionError.class)
    public ResponseEntity<Object> handleFormSubmissionInternalError(FormSubmissionError e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(InternalError.class)
    public ResponseEntity<Object> handleInternalError(InternalError e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(InvalidJWT.class)
    public ResponseEntity<Object> handleInvalidJWT(InvalidJWT e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MalformDataError.class)
    public ResponseEntity<Object> handleMalformDataError(MalformDataError e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MissingFieldsError.class)
    public ResponseEntity<Object> handleMissingFieldError(MissingFieldsError e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
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
    public ResponseEntity<Object> handleNotValidForm(NotValidForm e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
