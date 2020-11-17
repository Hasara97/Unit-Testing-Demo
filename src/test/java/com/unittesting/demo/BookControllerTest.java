package com.unittesting.demo;

import com.unittesting.demo.controller.BookController;
import com.unittesting.demo.model.Book;
import com.unittesting.demo.service.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnitTestingApplication.class)
public class BookControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).dispatchOptions(true).build();
    }

    @Test
    public void testStoreBook() throws Exception {

        Book book = new Book(123, "Lord of the Rings", "Tolkien", "Fantasy genre book");

        when(bookService.saveBook(book)).thenReturn(book);

        Assert.assertEquals(book, bookController.storeBook(book).getBody());


    }

    @Test
    public void testGetBook() throws Exception {

        Book book = new Book(123, "Lord of the Rings", "Tolkien", "Fantasy genre book");

        when(bookService.getBookById(123)).thenReturn(book);

        Assert.assertEquals(book, bookController.getBook(123).getBody());


    }

    @Test
    public void testGetAllBook() throws Exception {

        Book book1 = new Book(123, "Lord of the Rings", "Tolkien", "Fantasy genre book");
        Book book2 = new Book(456, "Origin", "Dan Brown", "Thriller/Mystery genre book");

        List<Book> books = new ArrayList<>();

        books.add(book1);
        books.add(book2);

        when(bookService.getAllBooks()).thenReturn(books);

        Assert.assertEquals(books, bookController.getAllBooks().getBody());

    }

    @Test
    public void testDeleteBookById() throws Exception {

        Book book = new Book(123, "Lord of the Rings", "Tolkien", "Fantasy genre book");

        bookController.deleteBookById(123);

        verify(bookService).deleteById(123);

    }

    @Test
    public void testUpdateBook() throws Exception {

        Book book = new Book(123, "Lord of the Rings", "Tolkien", "Fantasy genre book");
        Book bookUpdated = new Book(123, "Lord of the Rings UPDATED", "Tolkien", "Fantasy genre book");


        when(bookService.getBookById(123)).thenReturn(book);

        book.setBookName("Lord of the Rings UPDATED");

        when(bookService.updateBook(123, book)).thenReturn(book);

        Assert.assertEquals(bookUpdated.getBookName(), bookController.updateBook(123, book).getBody().getBookName());

    }

    

}
