package com.rohan.mockito.twoHours50Minutes.behaviorVerification;

import com.rohan.mockito.twoHours50Minutes.stubbing.BookRequest;

import java.time.LocalDate;
import java.util.List;

public class BookService {
    private BookRepository bookRepository;     // this is somethong that talks to database;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book){
        bookRepository.save(book);
    }
}
