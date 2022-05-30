package com.rohan.mockito.twoHours50Minutes.stubbing;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
class BookServiceTest {

    @InjectMocks
    public BookService bookService;

    @Mock
    public BookRepository bookRepository;

    @Test
    void calculateTotalCost() {
//        when(bookRepository.findBookByBookIds("bookId_1")).thenReturn(new Book("bookId_1" , "bookTitle_1" , 10 , LocalDate.now()));
//        when(bookRepository.findBookByBookIds("bookId_2")).thenReturn(new Book("bookId_2" , "bookTitle_2" , 34 , LocalDate.now()));
//        when(bookRepository.findBookByBookIds("bookId_3")).thenReturn(new Book("bookId_3" , "bookTitle_3" , 16 , LocalDate.now()));
        // Note the return type is OnGoingStubbing when(class.method).thenReturn()

        List<String> bookIds = new ArrayList<>(Arrays.asList("bookId_1" , "bookId_2" , "bookId_3"));

        // Another Way
        doReturn(new Book("bookId_1" , "bookTitle_1" , 10 , LocalDate.now())).when(bookRepository).findBookByBookIds("bookId_1");
        doReturn(new Book("bookId_2" , "bookTitle_2" , 34 , LocalDate.now())).when(bookRepository).findBookByBookIds("bookId_2");
        doReturn(new Book("bookId_3" , "bookTitle_3" , 16 , LocalDate.now())).when(bookRepository).findBookByBookIds("bookId_3");
        // Note this is different by things, doReturn takes Mock Object doReturn(Object).when(mockObject).methodName();

//        we can do something like this when we want to return same obj twice
//        when(bookRepository.findNewBooks("1234")).thenReturn(book_firstTime, book_SecondTime, book_ThirdTime);

//        this format is more readable
//        when(bookRepository.findNewBooks("1234"))
//                .thenReturn(book_firstTime)
//                .thenReturn(book_SecondTime)
//                .thenReturn(book_ThirdTime)

        int actualTotalCost = bookService.calculateTotalCost(bookIds);
        assertEquals(60,actualTotalCost);
    }

    @Test
    void addBookTest(){
        // Testing for method that doesnot returns anything
        // CONTINUE FROM 3 hour 36 Minutes https://www.youtube.com/watch?v=RfErIPo94bc
        Book book1 = new Book("bookId_1" , "bookTitle_1" , 10 , LocalDate.now());
        doNothing().when(bookRepository).save(book1);
        bookService.addBook(book1);
    }
    @Test
    void addBookTestWithBookRequest(){
        BookRequest bookRequest = new BookRequest("bookTitle_1" , 10 , LocalDate.now());
        /**
         * When Add book is called, at the last step we are calling saveBook to save it in out DB, which takes the book
         * and not the book and not the bookRequest, how do we tell the book(the one in void addBook(BookRequest) { Book book ... bookRepo.save };
         * */
        Book book1 = new Book("bookId_1" , "bookTitle_1" , 10 , LocalDate.now());
        doNothing().when(bookRepository).save(book1);
        /**
         * now the problem comes when we are saying that when we call with this book then do nothing,
         * but in addBook(BookRequest) we are creating yet another bookRequest
         * === ?? not clear , yes me too
         *
         * When we run the Testcase we can see that the TestCase got failed, with the exception org.mockito.exceptions.misusing.PotentialStubbingProblem:
         *
         * coz we are creating a new book so this is the reason we are getting the error, to resolve this, we need to override the .equals() method
         * in Book class, so that we can remove bookId check from it, then this will result in correct statement coz mockito uses == method for comparison=
         * */
        bookService.addBook(bookRequest);
    }
}


















