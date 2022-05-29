package com.rohan.mockito.test_doubles.mock;

import com.rohan.mockito.test_doubles.mock.Book;
import com.rohan.mockito.test_doubles.mock.BookRepository;
import lombok.Getter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Getter
public class BookRepositoryMock implements BookRepository {

    private int timeCalled = 0;
    private Book lastAddedBook = null;
    @Override
    public void save(Book book){

        timeCalled++;
        lastAddedBook = book;
    }

    /**
     * here you can see all the behaviour verification is with the mock itself
     * */
    public void verify(Book book, int times){
        assertEquals(times , timeCalled);
        assertEquals(book, lastAddedBook );
    }
}
