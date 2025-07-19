package com.formkio.formfio.services;

import com.formkio.formfio.model.UsersModel;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    public UserService() {}

    // This will be replaced by an actual way to extract the user info
    // when they call the API using either JWT or Cookies.
    public UsersModel getUserInfo() {
        UsersModel usersModel = new UsersModel();
        usersModel.setId(1);
        usersModel.setEmail("kevinl33078@gmail.com");
        usersModel.setAccountPlan(0);
        usersModel.setIsReferred(0);
        usersModel.setFreeTrail(0);
        return usersModel;
    }

}
