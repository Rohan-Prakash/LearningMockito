package com.rohan.mockito.test_doubles.spy;

import lombok.Getter;

@Getter
public class BookRepositorySpy implements BookRepository{

    private int timeCalled = 0;
    private Book lastAddedBook = null;
    @Override
    public void save(Book book){

        timeCalled++;
        lastAddedBook = book;

    }
}
