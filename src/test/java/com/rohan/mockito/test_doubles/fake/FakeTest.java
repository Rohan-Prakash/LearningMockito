package com.rohan.mockito.test_doubles.fake;

import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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

    @Test
    public void testFakeWithMockito(){
        BookRepository bookRepositoryMock = mock(BookRepository.class);
        BookService bookService = new BookService(bookRepositoryMock);
        List<Book> booksToAdd  = new ArrayList<>(Arrays.asList(
                    new Book("CD23" , "ComputerData" , 24 , LocalDate.now()),
                    new Book("BT94" , "BioTech1233" , 29 , LocalDate.now()),
                    new Book("Me13" , "Mechanical@3" , 23 , LocalDate.now()),
                    new Book("ME23" , "Mechanical29" , 19 , LocalDate.now())
                    )
        );
        booksToAdd.forEach(book -> bookService.addBook(book));
        when(bookRepositoryMock.findAll()).thenReturn(booksToAdd);

        assertEquals(4 , bookService.findNumberOfBooks());
    }

}





















