package com.rohan.mockito.test_doubles.fake;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FakeTest {

    @Test
    public void testFake(){
        BookRepository bookRepository = new FakeBookRepository();
        BookService bookService = new BookService(bookRepository);

        bookService.addBook(new Book("CD23" , "ComputerData" , 24 , LocalDate.now()));
        bookService.addBook(new Book("BT94" , "BioTech1233" , 29 , LocalDate.now()));
        bookService.addBook(new Book("Me13" , "Mechanical@3" , 23 , LocalDate.now()));
        bookService.addBook(new Book("ME23" , "Mechanical29" , 19 , LocalDate.now()));

        assertEquals(4 , bookService.findNumberOfBooks());

    }


}