package com.unittesting.demo;

import com.unittesting.demo.model.Book;
import com.unittesting.demo.repository.BookRepository;
import com.unittesting.demo.service.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnitTestingApplication.class)
public class BookServiceTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).dispatchOptions(true).build();
    }

    @Test
    public void testSaveBook() throws Exception {

        Book book = new Book(123, "Lord of the Rings", "Tolkien", "Fantasy genre book");

        Mockito.when(bookRepository.save(book)).thenReturn(book);

        Assert.assertEquals(book, bookService.saveBook(book));
    }

    @Test
    public void testGetBookById() throws Exception {

        Book book = new Book(123, "Lord of the Rings", "Tolkien", "Fantasy genre book");

        Mockito.when(bookRepository.findById((long) 123)).thenReturn(Optional.of(book));

        Assert.assertEquals(book, bookService.getBookById(123));
    }

    @Test
    public void testGetAllBooks() throws Exception {

        Book book1 = new Book(123, "Lord of the Rings", "Tolkien", "Fantasy genre book");
        Book book2 = new Book(456, "Origin", "Dan Brown", "Thriller/Mystery genre book");

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        Mockito.when(bookRepository.findAll()).thenReturn(books);

        Assert.assertEquals(books, bookService.getAllBooks());
        Assert.assertEquals(2, bookService.getAllBooks().size());

    }


    @Test
    public void testDeleteById() throws Exception {

        Book book = new Book(123, "Lord of the Rings", "Tolkien", "Fantasy genre book");

        bookService.deleteById(123);

        verify(bookRepository).deleteById((long) 123);

    }

    @Test
    public void testUpdateBook() throws Exception {

        Book book = new Book(123, "Lord of the Rings", "Tolkien", "Fantasy genre book");
        Book bookUpdated = new Book(123, "Lord of the Rings UPDATED", "Tolkien", "Fantasy genre book");

        Mockito.when(bookRepository.findById((long) 123)).thenReturn(Optional.of(book));

        book.setBookName("Lord of the Rings UPDATED");

        Mockito.when(bookRepository.save(book)).thenReturn(book);

        Assert.assertEquals(bookUpdated.getBookName(), bookService.updateBook(123, book).getBookName());

    }

    @Test(expected = NoSuchElementException.class)
    public void testGetBookByNonExistingId() throws Exception {

        Book book = new Book(123, "Lord of the Rings", "Tolkien", "Fantasy genre book");

        Mockito.when(bookRepository.findById((long) 123)).thenReturn(Optional.of(book));

        Assert.assertEquals(book, bookService.getBookById(456));
    }
}
