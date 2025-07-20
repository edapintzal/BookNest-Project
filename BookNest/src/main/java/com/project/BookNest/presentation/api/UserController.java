package com.project.BookNest.presentation.api;

import com.project.BookNest.application.service.LoginService;
import com.project.BookNest.application.service.RegisterService;
import com.project.BookNest.domain.model.Book;
import com.project.BookNest.domain.model.Cart;
import com.project.BookNest.domain.model.Member;
import com.project.BookNest.domain.model.User;
import com.project.BookNest.domain.service.CartService;
import com.project.BookNest.domain.service.UserService;
import com.project.BookNest.infrastructure.repository.BookRepositoryImpl;
import com.project.BookNest.infrastructure.repository.UserRepositoryImpl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class UserController {

    //all services have one common User Repo
    UserRepositoryImpl userRepository = new UserRepositoryImpl();

    LoginService loginService = new LoginService(userRepository);
    RegisterService registerService = new RegisterService(userRepository);
    UserService userService = new UserService(userRepository);
    CartService cartService;
    public UserController(BookRepositoryImpl bookRepository){
        cartService = new CartService(bookRepository);
    }

    //register servisini cagirir
    public boolean registerUser(Member user){
        return registerService.addUser(user);
    }
    //login servisini cagirir
    public boolean login(String login, String password) {
        return loginService.validate(login,password);
    }
    public User getUser(String login) {
        return userService.getUser(login);
    }
    public void addToCart(User user, Book book) {
        userRepository.addToCart(user,book);
    }
    public boolean showCart(User user){
        return cartService.showCart(user);
    }
    public void saveCart(User member){
        userRepository.saveCart(member);
    }
    public void deleteCart(User member){
        userRepository.deleteCart(member);
    }
    public boolean deleteFromCart(User member,String num){
        return userRepository.deleteFromCart(member,num);
    }
    public boolean isEmptyCart(User member){
       return userRepository.isEmptyCart(member);
    }
    public void deleteFromCartJson(User member, String choice){
        userRepository.deleteFromCartJson(member,choice);
    }
}
