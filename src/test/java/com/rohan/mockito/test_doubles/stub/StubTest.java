package com.rohan.mockito.test_doubles.stub;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StubTest {

    @Test
    public void demoStubMockito(){
        BookRepository bookRepositoryMock = mock(BookRepository.class);
        BookService bookService = new BookService(bookRepositoryMock);

        when(bookRepositoryMock.findNewBooks(7)).thenReturn(

                // this is our stubbed list
                Arrays.asList(
                new Book("bookID" , "bookTitle_100" , 100 , LocalDate.now()),
                new Book("bookID" , "bookTitle_500" , 500 , LocalDate.now())
                )
        );

        List<Book> bookReceived = bookService.getNewBooksWithAppliedDiscount(10 , 7);
        assertEquals(2 , bookReceived.size() );

        // they should have discount price as 100->90 and 500->450
        assertEquals(90 , bookReceived.get(0).getPrice());
        assertEquals(450 , bookReceived.get(1).getPrice());
    }

    @Test
    public void demoStub(){
        BookRepository bookRepositoryStub = new BookRepositoryStub();
        BookService bookService = new BookService(bookRepositoryStub);

        List<Book> bookReceived = bookService.getNewBooksWithAppliedDiscount(10 , 7);

        // we have hardCoded 2 books so we need 2 books in output
        assertEquals(2 , bookReceived.size() );

        // they should have discount price as 100->90 and 500->450
        assertEquals(90 , bookReceived.get(0).getPrice());
        assertEquals(450 , bookReceived.get(1).getPrice());
    }
}
