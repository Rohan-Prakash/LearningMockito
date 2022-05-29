package com.rohan.mockito.test_doubles.dummy;

import com.rohan.mockito.test_doubles.dummy.Book;
import com.rohan.mockito.test_doubles.dummy.BookService;
import com.rohan.mockito.test_doubles.dummy.BookRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DummyTest {

    @Test
    public void demoDummy(){
        BookRepository bookRepository = new FakeBookRepository();
        EmailService emailService = new DummyEmailService();
        BookService bookService = new BookService(bookRepository , emailService);

//        What if we try to use emailServie ? We have used 'throw new AssertionError(  )'
//        emailService.sendEmail("HH");

        bookService.addBook(new Book("CD23" , "ComputerData" , 24 , LocalDate.now()));
        bookService.addBook(new Book("BT94" , "BioTech1233" , 29 , LocalDate.now()));
        bookService.addBook(new Book("Me13" , "Mechanical@3" , 23 , LocalDate.now()));
        bookService.addBook(new Book("ME23" , "Mechanical29" , 19 , LocalDate.now()));

        assertEquals(4 , bookService.findNumberOfBooks());
    }
}
