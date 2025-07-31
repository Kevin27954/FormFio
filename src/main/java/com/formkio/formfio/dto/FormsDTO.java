package com.formkio.formfio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.formkio.formfio.model.UsersModel;

public class FormsDTO {
    @JsonIgnore
    private UsersModel usersModel;

    public String endpoint;
    public String formName;
    public String description;

    public FormsDTO() {
    }

    public FormsDTO(UsersModel usersModel) {
        this.usersModel = usersModel;
    }

    public FormsDTO(UsersModel usersModel, String formName, String endpoint) {
        this.usersModel = usersModel;
        this.formName = formName;
        this.endpoint = endpoint;
    }

    public UsersModel getUsersModel() {
        return usersModel;
    }

    public void setUsersModel(UsersModel usersModel) {
        this.usersModel = usersModel;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

}
