package com.rohan.mockito.test_doubles.stub;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StubTest {

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
