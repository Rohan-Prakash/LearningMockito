package com.rohan.mockito.test_doubles.stub;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BookRepositoryStub implements BookRepository{

    public List<Book> findNewBooks(int days){
        /**
         * since we need to test the discount logic, so we dont really care of price
         * */
        return Arrays.asList(
                new Book("bookID" , "bookTitle_100" , 100 , LocalDate.now()),
                new Book("bookID" , "bookTitle_500" , 500 , LocalDate.now())
        );
    }

}
