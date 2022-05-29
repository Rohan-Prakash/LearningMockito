package com.rohan.mockito.test_doubles.dummy;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DummyTest {

    /**
     * We are required to provide EmailService but we are not using that in our testing.
     * */
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
    @Test
    public void demoDummyMockito(){
        BookRepository bookRepositoryMock =  mock(BookRepository.class);
        EmailService emailServiceMock = mock(EmailService.class);
        BookService bookService = new BookService(bookRepositoryMock , emailServiceMock);

//        What if we try to use emailServie ? We have used 'throw new AssertionError(  )'
//        emailService.sendEmail("HH");

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
