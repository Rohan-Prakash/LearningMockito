package com.rohan.mockito.twoHours50Minutes.Base;

import java.util.List;

public interface BookRepository {
    List<Book> findNewBooks(int days);
}
