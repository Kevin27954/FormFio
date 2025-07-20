package com.formkio.formfio.dto;

import com.formkio.formfio.model.UsersModel;

public class FormsDTO {
    // Can't be empty
    private String endpoint;
    private UsersModel usersModel;
    private String name;

    // Can be empty
    private String description;

    public FormsDTO() {
    }

    public FormsDTO(UsersModel usersModel) {
        this.usersModel = usersModel;
    }

    public FormsDTO(UsersModel usersModel, String name, String endpoint) {
        this.usersModel = usersModel;
        this.name = name;
        this.endpoint = endpoint;
    }

    public UsersModel getUsersModel() {
        return usersModel;
    }

    public void setUsersModel(UsersModel usersModel) {
        this.usersModel = usersModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
