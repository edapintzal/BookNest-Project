package com.project.BookNest.domain.service;

import com.project.BookNest.domain.model.Book;
import com.project.BookNest.domain.model.Member;
import com.project.BookNest.domain.model.User;
import com.project.BookNest.domain.repository.BookRepository;

public class CartService {

    BookRepository bookRepository;
    public CartService(BookRepository bookRepositoryNew) {
        bookRepository = bookRepositoryNew;
    }


    public boolean showCart(User member) {
       return member.showCart();
    }








}
