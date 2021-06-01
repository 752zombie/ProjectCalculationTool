package com.example.projecttool.services;

import com.example.projecttool.models.user.User;
import com.example.projecttool.repositories.UserRepository;

import java.sql.SQLException;

// Magnus

public class LoginService {

    public static User createUser(String name, String email, String password) throws SQLException {

        UserRepository.addUser(name, email, password);
        return UserRepository.attemptLogin(email, password);
    }

    public static User attemptLogin(String email, String password) throws SQLException {


        User user = UserRepository.attemptLogin(email, password);
        if (user == null){
         throw new SQLException();
        }
        return user;

    }

}
