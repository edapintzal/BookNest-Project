package com.project.BookNest.presentation.api;

import com.project.BookNest.infrastructure.repository.BookRepositoryImpl;

public class Director {

    private BookRepositoryImpl bookRepository= new BookRepositoryImpl();

    public BookController createBookController(){
        return new BookController(bookRepository);
    }
    public UserController createUserController(){
        return new UserController(bookRepository);
    }
    public AdminController createAdminController(){ return new AdminController(bookRepository);}
}
