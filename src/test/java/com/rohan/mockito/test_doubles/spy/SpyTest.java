package com.rohan.mockito.test_doubles.spy;

import com.rohan.mockito.test_doubles.spy.BookRepository;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class SpyTest {

    /**
     * we need to test that this 'boolRepository.save' method is even called or not, since this doesn't returns anything
     * so we might not be able to verify that this was even called or not,
     *
     * we cant go to DB by instantiating the bookRepoDb just for testing purpose, therefore we bring in the spy, which
     * behaves something like a stub + also a capability of recording the information, or recording the interaction
     * made with it
     * */

    @Test
    public void demoSpyMockito(){
        BookRepository bookRepositoryMock_spy = spy(BookRepository.class);
        BookService bookService = new BookService(bookRepositoryMock_spy);

        List<Book> booksThatWeWouldBeAdding = new ArrayList<Book>(
                Arrays.asList(
                        new Book("bookId_1" , "bookTitle_1", 23 , LocalDate.now()),
                        new Book("bookId_2" , "bookTitle_2", 21 , LocalDate.now()),
                        new Book("bookId_3" , "bookTitle_3", 29 , LocalDate.now()),
                        new Book("bookId_4" , "bookTitle_4", 90 , LocalDate.now())
                )
        ) ;
        booksThatWeWouldBeAdding.forEach(book -> bookService.addBook(book));
        verify(bookRepositoryMock_spy, times(1)).save(booksThatWeWouldBeAdding.get(0));
        verify(bookRepositoryMock_spy, times(1)).save(booksThatWeWouldBeAdding.get(1));
        verify(bookRepositoryMock_spy, times(1)).save(booksThatWeWouldBeAdding.get(2));
        verify(bookRepositoryMock_spy, times(1)).save(booksThatWeWouldBeAdding.get(3));
    }

    @Test
    public void demoSpy(){
        // we need to see that does the service even calls for 'bookRepository.save' method
        BookRepositorySpy bookRepositorySpy = new BookRepositorySpy();
        BookService bookService = new BookService(bookRepositorySpy);

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
        booksThatWeWouldBeAdding.forEach(book -> bookService.addBook(book));

        System.out.println("we called method bookRepo.save " + bookRepositorySpy.getTimeCalled() + " many times");

        assertEquals(booksThatWeWouldBeAdding.size() , bookRepositorySpy.getTimeCalled());
        assertEquals(booksThatWeWouldBeAdding.get(booksThatWeWouldBeAdding.size()-1) , bookRepositorySpy.getLastAddedBook());
    }
}
