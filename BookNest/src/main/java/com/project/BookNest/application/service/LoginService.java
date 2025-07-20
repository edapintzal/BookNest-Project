package com.project.BookNest.application.service;


import com.project.BookNest.domain.model.User;
import com.project.BookNest.infrastructure.repository.UserRepositoryImpl;

import java.util.List;

//this class is responsible for Login
public class LoginService {

    UserRepositoryImpl userRepository;
    public LoginService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }
    //login kontrolü
    public boolean validate(String login, String password) {

        return userRepository.validate(login,password);
    }
}
