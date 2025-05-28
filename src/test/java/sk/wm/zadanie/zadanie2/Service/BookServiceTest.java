package sk.wm.zadanie.zadanie2.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sk.wm.zadanie.zadanie2.DAO.BookCopyEntity;
import sk.wm.zadanie.zadanie2.DAO.BookEntity;
import sk.wm.zadanie.zadanie2.DTO.BookCopy;
import sk.wm.zadanie.zadanie2.DTO.BookEntityExt;
import sk.wm.zadanie.zadanie2.Repository.BookCopyRepository;
import sk.wm.zadanie.zadanie2.Repository.BookRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookCopyRepository bookCopyRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBooks_ShouldReturnListOfBooks() {
        // Arrange
        BookEntity book = new BookEntity();
        book.setTitle("Test Book");
        List<BookEntity> expectedBooks = Arrays.asList(book);
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        // Act
        List<BookEntity> result = bookService.getBooks();

        // Assert
        assertEquals(expectedBooks, result);
        verify(bookRepository).findAll();
    }

    @Test
    void getBookById_ExistingBook_ShouldReturnBookWithCopies() {
        // Arrange
        BookEntity book = new BookEntity();
        book.setId(1L);
        book.setTitle("Test Book");

        BookCopy copy = new BookCopy(1L, true);
        List<BookCopy> copies = Arrays.asList(copy);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookCopyRepository.findCopyByBookId(1L)).thenReturn(copies);

        // Act
        BookEntityExt result = bookService.getBookById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(book.getId(), result.getId());
        assertEquals(book.getTitle(), result.getTitle());
    }

    @Test
    void createBook_ValidBook_ShouldReturnSavedBook() {
        // Arrange
        BookEntity book = new BookEntity();
        book.setTitle("New title");
        book.setIsbn("9780316769488");
        when(bookRepository.save(any(BookEntity.class))).thenReturn(book);

        // Act
        BookEntity result = bookService.createBook(book);

        // Assert
        assertEquals(book, result);
        verify(bookRepository).save(book);
    }

    @Test
    void updateBook_ExistingBook_ShouldReturnUpdatedBook() {
        // Arrange
        BookEntity existingBook = new BookEntity();
        existingBook.setId(1L);
        existingBook.setTitle("Old Title");

        BookEntity updatedBook = new BookEntity();
        updatedBook.setTitle("New Title");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(BookEntity.class))).thenReturn(existingBook);

        // Act
        BookEntity result = bookService.updateBook(updatedBook, 1L);

        // Assert
        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
    }

    @Test
    void deleteBook_ExistingBook_ShouldDeleteBookAndCopies() {
        // Arrange
        BookCopyEntity copy = new BookCopyEntity();
        List<BookCopyEntity> copies = Arrays.asList(copy);
        when(bookCopyRepository.findAllByBookId(1L)).thenReturn(copies);

        // Act
        boolean result = bookService.deleteBook(1L);

        // Assert
        assertTrue(result);
        verify(bookCopyRepository).deleteAll(copies);
        verify(bookRepository).deleteById(1L);
    }

    @Test
    void getBookCopies_ExistingBook_ShouldReturnCopies() {
        // Arrange
        BookCopy copy = new BookCopy(1L, true);
        List<BookCopy> expectedCopies = Arrays.asList(copy);
        when(bookCopyRepository.findCopyByBookId(1L)).thenReturn(expectedCopies);

        // Act
        List<BookCopy> result = bookService.getBookCopies(1L);

        // Assert
        assertEquals(expectedCopies, result);
    }

    @Test
    void addBookCopy_ValidBookId_ShouldReturnNewCopy() {
        // Arrange
        BookCopyEntity newCopy = new BookCopyEntity();
        newCopy.setId(1L);
        newCopy.setAvailable(true);
        when(bookCopyRepository.save(any(BookCopyEntity.class))).thenReturn(newCopy);

        // Act
        BookCopyEntity result = bookService.addBookCopy(1L);

        // Assert
        assertNotNull(result);
        assertTrue(result.getAvailable());
        verify(bookCopyRepository).save(any(BookCopyEntity.class));
    }

    @Test
    void updateBookCopy_ValidCopy_ShouldReturnUpdatedCopy() {
        // Arrange
        BookCopyEntity existingCopy = new BookCopyEntity();
        existingCopy.setId(1L);
        existingCopy.setAvailable(true);

        BookCopyEntity updateRequest = new BookCopyEntity();
        updateRequest.setAvailable(false);

        when(bookCopyRepository.findById(1L)).thenReturn(Optional.of(existingCopy));
        when(bookCopyRepository.save(any(BookCopyEntity.class))).thenReturn(existingCopy);

        // Act
        BookCopyEntity result = bookService.updateBookCopy(1L, updateRequest);

        // Assert
        assertNotNull(result);
        assertFalse(result.getAvailable());
    }
}