package com.project.BookNest.domain.service;

import com.project.BookNest.domain.model.Book;
import com.project.BookNest.domain.repository.BookRepository;
import com.project.BookNest.infrastructure.repository.BookRepositoryImpl;

import java.io.IOException;

public class BookService {
    BookRepositoryImpl bookRepository;
    public BookService(BookRepositoryImpl bookRepositoryNew) {
        bookRepository = bookRepositoryNew;
    }



    public void reviewBook(String review, String id, String person, int rating) throws IOException {
        bookRepository.reviewBook(review,id,person,rating);
    }
    public void loadBooks(){
        bookRepository.loadBooks();
    }
    public void addBook(Book newBook) throws IOException { bookRepository.addBook(newBook);}
    public void deleteBook(String id) throws IOException {bookRepository.deleteBook(id);}
    public void updateBook(String id , int updateLocation , String newInfo) throws IOException {bookRepository.updateBook(id,updateLocation,newInfo);}
    public String nameSearch(String title){
        return bookRepository.nameSearch(title);
    }
    public void authorSearch(String author){
        bookRepository.authorSearch(author);
    }
    public void mainGenreSearch(String genre){
        bookRepository.mainGenreSearch(genre);
    }
    public void showReviews(String id){
        bookRepository.showReviews(id);
    }
    public String getBooksId(String name){
        return bookRepository.getBooksId(name);
    }
    public Book getBookById(String id) throws IOException {
        return bookRepository.getBookById(id);
    }
}
