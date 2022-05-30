package com.rohan.mockito.twoHours50Minutes.stubbing;

import java.time.LocalDate;
import java.util.List;

public class BookService {
    private BookRepository bookRepository;     // this is somethong that talks to database;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

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

    public int calculateTotalCost(List<String> bookIds){
        int totalCost = 0 ;
        for(String bookId : bookIds)
           totalCost += ((Book)bookRepository.findBookByBookIds(bookId)).getPrice( );

        return totalCost;
    }
    public void addBook(Book book){
        bookRepository.save(book);
    }
    public void addBook(BookRequest bookRequest){
        Book book = Book.builder()
                        .bookId("bookId")
                                .title("bookTitle")
                                        .price(24)
                                                .publishedDate(LocalDate.now())
                                                        .build();

        bookRepository.save(book);
    }
}
