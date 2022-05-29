package com.rohan.mockito.test_doubles.dummy;

import com.rohan.mockito.test_doubles.dummy.Book;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FakeBookRepository implements BookRepository {

    /**
     * instead of actual database we can use inMemoryDatabase of HashMap or List
     * */

    Map<String, com.rohan.mockito.test_doubles.dummy.Book> bookStore = new HashMap<>();
    @Override
    public void save(com.rohan.mockito.test_doubles.dummy.Book book) {
        bookStore.put(book.getBookId() , book);
    }


    @Override
    public Collection<Book> findAll() {
        return bookStore.values();
    }
}
