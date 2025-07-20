package com.project.BookNest.presentation.api;

import com.project.BookNest.domain.model.Book;
import com.project.BookNest.infrastructure.repository.BookRepositoryImpl;

import java.io.IOException;

public class AdminController {
    private final BookRepositoryImpl bookRepository;

    public AdminController(BookRepositoryImpl bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBookToSystem(Book book) throws IOException {
        bookRepository.addBook(book);
    }
}

