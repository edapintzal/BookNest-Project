package com.project.BookNest.domain.service;

import com.project.BookNest.domain.model.Member;
import com.project.BookNest.domain.model.User;
import com.project.BookNest.domain.repository.UserRepository;

public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public User getUser(String login) {
        return userRepository.getUser(login);
    }
}

