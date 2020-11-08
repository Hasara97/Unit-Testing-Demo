package com.unittesting.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Library {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;

    private String bookName;

    private String author;

    private String bookDescription;

    public Library(long bookId, String bookName, String author, String bookDescription) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.bookDescription = bookDescription;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }
}
