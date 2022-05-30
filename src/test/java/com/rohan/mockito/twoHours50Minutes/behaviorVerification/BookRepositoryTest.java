package com.rohan.mockito.twoHours50Minutes.behaviorVerification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//@RunWith(MockitoJUnitRunner.class)


@ExtendWith(MockitoExtension.class)
class BookRepositoryTestBehaviourVerification {

    @InjectMocks
    public BookService bookService;

    @Mock
    public BookRepository bookRepository;

    @Test
    public void testAddBook(){
        Book book = new Book("bookID" , "bookTitle_100" , 100 , LocalDate.now());
        bookService.addBook(book);

        verify(bookRepository, times(1)).save(book);
    }

}