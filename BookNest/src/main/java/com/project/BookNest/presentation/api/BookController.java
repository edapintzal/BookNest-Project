package com.project.BookNest.presentation.api;

import com.project.BookNest.domain.model.Book;
import com.project.BookNest.domain.service.BookService;
import com.project.BookNest.infrastructure.repository.BookRepositoryImpl;

import java.io.IOException;

public class BookController {
    BookService bookService;
    public BookController(BookRepositoryImpl bookRepository){
        bookService = new BookService(bookRepository);
    }



    public void loadBooks(){
        bookService.loadBooks();
    }
    public void addBook(Book newBook) throws IOException { bookService.addBook(newBook);}
    public void deleteBook(String id) throws IOException {bookService.deleteBook(id);}
    public void updateBook(String id , int updateLocation , String newInfo) throws IOException {bookService.updateBook(id,updateLocation,newInfo);}
    public String nameSearch(String name){
        return bookService.nameSearch(name);
    }
    public void authorSearch(String author){
        bookService.authorSearch(author);
    }
    public void mainGenreSearch(String genre){
        bookService.mainGenreSearch(genre);
    }
    public void reviewBook(String review, String id, String person, int rating) throws IOException {
        bookService.reviewBook(review,id,person,rating);
    }
    public void showReviews(String id){
        bookService.showReviews(id);
    }
    public String getBooksId(String name){
        return bookService.getBooksId(name);
    }
    public Book getBookById(String id) throws IOException {
        return bookService.getBookById(id);
    }



}
