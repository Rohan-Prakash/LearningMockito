package com.rohan.mockito.annotations.support;

import com.rohan.mockito.annotations.support.Book;
import com.rohan.mockito.annotations.support.BookRepository;
import com.rohan.mockito.annotations.support.BookService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
public class AnnotationsTest {

    @Rule   // jUnit4 this is the third way after @ExtendsWith @RunWith and  MockitoAnnotations.initMocks(this) in before, this will allow annotations processing
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    // NOTE : the above line should be always public


    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

//    @Before
//    public void beforeEach(){
//        // another method is this if @ExtendWith(MockitoExtension.class) and @RunWith(MockitoJUnitRunner.class)
//        // is not working, this will allow annotations processing
//        MockitoAnnotations.initMocks(this);
//    }


    @Test
    public void demoCreateMocksUsingAnnotations(){
        BookService bookService = new BookService(bookRepository);

        when(bookRepository.findNewBooks(7)).thenReturn(

                // this is our stubbed list
                Arrays.asList(
                        new Book("bookID" , "bookTitle_100" , 100 , LocalDate.now()),
                        new Book("bookID" , "bookTitle_500" , 500 , LocalDate.now())
                )
        );

        List<Book> bookReceived = bookService.getNewBooksWithAppliedDiscount(10 , 7);
        assertEquals(2 , bookReceived.size() );

        // they should have discount price as 100->90 and 500->450
        assertEquals(90 , bookReceived.get(0).getPrice());
        assertEquals(450 , bookReceived.get(1).getPrice());
    }
}
