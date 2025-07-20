package com.project.BookNest.domain.repository;

import com.project.BookNest.domain.model.Book;
import com.project.BookNest.domain.model.Member;
import com.project.BookNest.domain.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public interface BookRepository {




    public  void loadBooks();

    public void addBook(Book newBook) throws IOException;
    public void deleteBook(String id) throws IOException;
    public void updateBook(String id , int updateLocation , String newInfo) throws IOException;
    public String nameSearch(String name);
    public void authorSearch(String author);
    public void mainGenreSearch(String genre);
    public void addToCart(User member, String id);
    public void reviewBook(String review, String id, String person, int rating) throws IOException;


}
