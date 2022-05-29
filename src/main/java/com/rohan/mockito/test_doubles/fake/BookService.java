package com.rohan.mockito.test_doubles.fake;

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
    public void addBook(Book book){
        bookRepository.save(book);
    }
    public int findNumberOfBooks(){
        return bookRepository.findAll().size();
    }
}
