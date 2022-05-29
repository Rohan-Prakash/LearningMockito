package com.rohan.mockito.test_doubles.spy;

/**
 * we need to test that this 'boolRepository.save' method is even called or not, since this doesn't returns anything
 * so we might not be able to verify that this was even called or not,
 *
 * we cant go to DB by instantiating the bookRepoDb just for testing purpose, therefore we bring in the spy, which
 * behaves something like a stub + also a capability of recording the information, or recording the interaction
 * made with it
 * */
public class BookService {
    private BookRepository bookRepository;     // this is somethong that talks to database;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public void addBook(Book book){
        bookRepository.save(book);
    }
}
