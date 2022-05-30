package com.rohan.mockito.twoHours50Minutes.stubbing;

import java.util.List;

public interface BookRepository {
    List<Book> findNewBooks(int days);

    Object findBookByBookIds(String bookId);

    void save(Book book);
}
