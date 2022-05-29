package com.rohan.mockito.test_doubles.mock;


import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


public class MockTest {

    /**
     * we need to test that this 'boolRepository.save' method is even called or not, since this doesn't returns anything
     * so we might not be able to verify that this was even called or not,
     *
     * we cant go to DB by instantiating the bookRepoDb just for testing purpose, therefore we bring in the spy, which
     * behaves something like a stub + also a capability of recording the information, or recording the interaction
     * made with it
     * */

    int saveCalled = 0 ;
    Book lastBookAdded = null;
    @Test
    public void demoMock(){
        // we need to see that does the service even calls for 'bookRepository.save' method
        BookRepositoryMock bookRepositoryMock = new BookRepositoryMock();
        BookService bookService = new BookService(bookRepositoryMock);

        List<Book> booksThatWeWouldBeAdding = new ArrayList<Book>(
                Arrays.asList(
                        new Book("bookId_1" , "bookTitle_1", 23 , LocalDate.now()),
                        new Book("bookId_2" , "bookTitle_2", 21 , LocalDate.now()),
                        new Book("bookId_3" , "bookTitle_3", 29 , LocalDate.now()),
                        new Book("bookId_4" , "bookTitle_4", 90 , LocalDate.now())

                )
        ) ;

        /**
         * we have added 4 books, so we expect that no of times that bookService makes 4 calls to bookRepository
         * and bookRepository calls 4 times 'bookRepository.save' method;
         * */
        booksThatWeWouldBeAdding.forEach(book -> {bookService.addBook(book) ; lastBookAdded = book; });
        /**
         * How is this different than spy ?
         * behaviour verification for mock is done here only
         * */
        bookRepositoryMock.verify(
                        booksThatWeWouldBeAdding.get(booksThatWeWouldBeAdding.size()-1),
                        booksThatWeWouldBeAdding.size()
        );
    };
    @Test
    public void demoMockMockito(){
        BookRepository bookRepositoryMock = mock(BookRepository.class);
        BookService bookService = new BookService(bookRepositoryMock);

        List<Book> booksThatWeWouldBeAdding = new ArrayList<Book>(
                Arrays.asList(
                        new Book("bookId_1" , "bookTitle_1", 23 , LocalDate.now()),
                        new Book("bookId_2" , "bookTitle_2", 21 , LocalDate.now()),
                        new Book("bookId_3" , "bookTitle_3", 29 , LocalDate.now()),
                        new Book("bookId_4" , "bookTitle_4", 90 , LocalDate.now())

                )
        ) ;
        booksThatWeWouldBeAdding.forEach(book -> {bookService.addBook(book) ; lastBookAdded = book; });
        verify(bookRepositoryMock).save(booksThatWeWouldBeAdding.get(booksThatWeWouldBeAdding.size()-1));

//         if i want to verify that program didn't called up for book_1
//        verify(bookRepositoryMock, times(0)).save(booksThatWeWouldBeAdding.get(1));
    }
}














