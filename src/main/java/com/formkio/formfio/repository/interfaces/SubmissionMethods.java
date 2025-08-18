package com.formkio.formfio.repository.interfaces;

import com.formkio.formfio.dto.SubmissionDTO;
import com.formkio.formfio.model.SubmissionsModel;

import java.util.List;
import java.util.Map;

public interface SubmissionMethods {
    /**
     * This creates a new form submission given a submissionDTO
     * It is best that all fields be filled.
     *
     * @param submissionDTO
     */
    void createNewFormSubmission(SubmissionsModel submissionsModel) ;

    /**
     * Grabs the submissions for a specific form endpoint
     *
     * @param endpoint, params
     * @return
     */
    List<SubmissionDTO> getFormSubmissions(String endpoint, Map<String, String> params ) ;
}
