package com.formkio.formfio.repository.interfaces;

import com.formkio.formfio.dto.SubmissionDTO;

import java.util.List;

public interface SubmissionMethods {
    /**
     * This creates a new form submission given a submissionDTO
     * It is best that all fields be filled.
     * @param submissionDTO
     */
    void createNewFormSubmission(SubmissionDTO submissionDTO) ;
}
