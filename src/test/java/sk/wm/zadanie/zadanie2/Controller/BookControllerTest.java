package sk.wm.zadanie.zadanie2.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sk.wm.zadanie.zadanie2.DAO.BookCopyEntity;
import sk.wm.zadanie.zadanie2.DAO.BookEntity;
import sk.wm.zadanie.zadanie2.DTO.BookCopy;
import sk.wm.zadanie.zadanie2.DTO.BookEntityExt;
import sk.wm.zadanie.zadanie2.Service.BookService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBooks_ShouldReturnListOfBooks() {
        // Arrange
        BookEntity book = new BookEntity();
        book.setId(1L);
        book.setTitle("Test Book");
        List<BookEntity> expectedBooks = Arrays.asList(book);
        when(bookService.getBooks()).thenReturn(expectedBooks);

        // Act
        ResponseEntity<List<BookEntity>> response = bookController.getBooks();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBooks, response.getBody());
    }

    @Test
    void createBook_ValidBook_ShouldReturnCreatedBook() {
        // Arrange
        BookEntity book = new BookEntity();
        book.setTitle("New Book");
        when(bookService.createBook(any(BookEntity.class))).thenReturn(book);

        // Act
        ResponseEntity<Object> response = bookController.createBook(book);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    void createBook_InvalidISBN_ShouldReturnBadRequest() {
        // Arrange
        BookEntity book = new BookEntity();
        when(bookService.createBook(any(BookEntity.class)))
                .thenThrow(new IllegalArgumentException("Invalid ISBN"));

        // Act
        ResponseEntity<Object> response = bookController.createBook(book);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Invalid ISBN"));
    }

    @Test
    void getBookById_ExistingBook_ShouldReturnBook() {
        // Arrange
        BookEntityExt book = new BookEntityExt();
        book.setId(1L);
        when(bookService.getBookById(1L)).thenReturn(book);

        // Act
        ResponseEntity<BookEntityExt> response = bookController.getBook(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    void getBookCopies_ExistingCopies_ShouldReturnCopiesList() {
        // Arrange
        BookCopy copy = new BookCopy(1L, true);
        List<BookCopy> copies = Arrays.asList(copy);
        when(bookService.getBookCopies(1L)).thenReturn(copies);

        // Act
        ResponseEntity<List<BookCopy>> response = bookController.getBookCopies(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(copies, response.getBody());
    }

    @Test
    void getBookCopies_NoCopies_ShouldReturnNotFound() {
        // Arrange
        when(bookService.getBookCopies(anyLong())).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<BookCopy>> response = bookController.getBookCopies(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateBookCopy_ValidUpdate_ShouldReturnUpdatedCopy() {
        // Arrange
        BookCopyEntity copy = new BookCopyEntity();
        copy.setId(1L);
        when(bookService.updateBookCopy(anyLong(), any(BookCopyEntity.class))).thenReturn(copy);

        // Act
        ResponseEntity<BookCopyEntity> response = bookController.updateBookCopy(1L, 1L, copy);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(copy, response.getBody());
    }

    @Test
    void deleteBook_ShouldReturnNoContent() {
        // Act
        ResponseEntity<Void> response = bookController.deleteBook(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}