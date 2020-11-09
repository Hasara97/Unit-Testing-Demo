package com.unittesting.demo.service;

import com.unittesting.demo.model.Book;
import com.unittesting.demo.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    public Book saveLibrary(Book book) {

        LOGGER.info("Storing the book");
        return bookRepository.save(book);
    }

    public Book getBookById(long bookId) {

        return bookRepository.findById(bookId).get();
    }

    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }

    public void deleteById(long bookId) {

        LOGGER.info("Deleting a book");
        bookRepository.deleteById(bookId);
    }

    public Book updateBook(long bookId, Book book) {

        Book bookToBeUpdated = getBookById(bookId);

        bookToBeUpdated.setBookName(book.getBookName());
        bookToBeUpdated.setAuthor(book.getAuthor());
        bookToBeUpdated.setBookDescription(book.getBookDescription());

        LOGGER.info("Updating a book record");
        return bookRepository.save(bookToBeUpdated);


    }


}
