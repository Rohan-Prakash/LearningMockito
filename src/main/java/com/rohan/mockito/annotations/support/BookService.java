package com.rohan.mockito.annotations.support;

import java.util.List;

/**
 * if BookService is out Class under test, and its external depandency is BookRepository, so duty of testDouble is
 * we can test BookService without actual implementation of BookRepository, we can create testDouble for BookRepo
 *
 * */
public class BookService {
    private BookRepository bookRepository;     // this is somethong that talks to database;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * now here we just need to check whether our discount logic is working or now, we will do stubbing, as we dont need
     * actual DB to test the discounted price, we will give our own data and check if dicount us being claculated or noe
     * */
    public List<Book> getNewBooksWithAppliedDiscount(int discountRate, int days){
        List<Book> newBooks = bookRepository.findNewBooks(days);

        // 500 apply 10% -> 10% of 500 -> 50 -> 500-50 ->450

        for(Book book:newBooks){
            int price = book.getPrice();
            int newPrice = price - (discountRate*price/100);
            book.setPrice(newPrice);
        }
        return newBooks;
    }
}
