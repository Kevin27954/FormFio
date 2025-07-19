package com.formkio.formfio.dto;

import com.formkio.formfio.exceptions.MissingValueError;
import com.formkio.formfio.model.UsersModel;

import java.util.Date;
import java.util.Map;

public class FormsDTO {
    // Can't be empty
    private String endpoint;
    private UsersModel usersModel;
    private String name;
    private String description;

    // Can be empty
    private int id;
    private Date created_date;

    public FormsDTO() {
    }

    public FormsDTO(UsersModel usersModel) {
        this.usersModel = usersModel;
    }

    public FormsDTO(UsersModel usersModel, String name, String description) {
        this.usersModel = usersModel;
        this.name = name;
        this.description = description;
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

    public FormsDTO setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public FormsDTO parse(Map<String, String> reqBody) throws MissingValueError {
        try {
            if(!reqBody.containsKey("name")) {
                throw new MissingValueError("reqbody did not contain: < name >");
            }
            this.setName(reqBody.get("name"));
            this.setDescription(reqBody.get("description"));

            return this;
        } catch (Exception e) {
            throw new MissingValueError("reqbody did not contain: <"+">");
        }
    }
}
