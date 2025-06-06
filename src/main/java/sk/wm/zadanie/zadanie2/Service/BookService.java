package sk.wm.zadanie.zadanie2.Service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sk.wm.zadanie.zadanie2.DAO.BookCopyEntity;
import sk.wm.zadanie.zadanie2.DAO.BookEntity;
import sk.wm.zadanie.zadanie2.DTO.BookCopy;
import sk.wm.zadanie.zadanie2.DTO.BookEntityExt;
import sk.wm.zadanie.zadanie2.Exceptions.InvalidIsbnException;
import sk.wm.zadanie.zadanie2.Repository.BookCopyRepository;
import sk.wm.zadanie.zadanie2.Repository.BookRepository;
import java.util.*;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;

    public BookService(BookRepository bookRepository, BookCopyRepository bookCopyRepository) {
        this.bookRepository = bookRepository;
        this.bookCopyRepository = bookCopyRepository;
    }

    public List<BookEntity> getBooks() {
        return this.bookRepository.findAll();
    }

    public Page<BookEntity> getBooks(int page, int size, String sortBy, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return this.bookRepository.findAll(pageable);
    }

    public BookEntityExt getBookById(long id) throws IllegalArgumentException{

        BookEntity book = this.bookRepository.findById(id).orElse(null);

        List<BookCopy> listOfBookCopies = this.bookCopyRepository.findCopyByBookId(id);

        BookEntityExt bookExt = new BookEntityExt();
        if (book != null) {
            bookExt.setId(book.getId());
            bookExt.setTitle(book.getTitle());
            bookExt.setAuthor(book.getAuthor());
            bookExt.setPublishedYear(book.getPublishedYear());
            bookExt.setIsbn(book.getIsbn());
            bookExt.setCopies(listOfBookCopies);
        }
        return bookExt; // or throw an exception
    }

    public BookEntity createBook (BookEntity bookEntity) throws InvalidIsbnException, DataIntegrityViolationException {
        // Validate ISBN format
        if (!bookEntity.getIsbn().matches("\\d{9}[\\dX]|\\d{13}")) {
            throw new InvalidIsbnException("Invalid ISBN format");
        }

        // Logic to create a book
        return this.bookRepository.save(bookEntity);
    }

    public BookEntity updateBook (BookEntity bookEntity, Long id) throws InvalidIsbnException, DataIntegrityViolationException {
        // Validate ISBN format
        if (!bookEntity.getIsbn().matches("\\d{9}[\\dX]|\\d{13}")) {
            throw new InvalidIsbnException("Invalid ISBN format");
        }

        // Logic to update a book
        BookEntity existingBook = this.bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            if(bookEntity.getTitle() != null){
                existingBook.setTitle(bookEntity.getTitle());
            }
            if (bookEntity.getAuthor() != null) {
                existingBook.setAuthor(bookEntity.getAuthor());
            }
            if (bookEntity.getPublishedYear() != null) {
                existingBook.setPublishedYear(bookEntity.getPublishedYear());
            }
            if (bookEntity.getIsbn() != null) {
                existingBook.setIsbn(bookEntity.getIsbn());
            }
            return this.bookRepository.save(existingBook);
        }
        return null; // or throw an exception
    }

    public boolean deleteBook (Long id) {
        // Remove all copies
        List<BookCopyEntity> bookCopies = this.bookCopyRepository.findAllByBookId(id);
        if (bookCopies != null && !bookCopies.isEmpty()) {
            this.bookCopyRepository.deleteAll(bookCopies);
        }
        // Remove the book itself
        try {
            this.bookRepository.deleteById(id);
        } catch (Exception e) {
            return false; // or throw an exception
        }
        return true;
    }

    public List<BookCopy> getBookCopies(Long id) {
        List<BookCopy> bookCopies = this.bookCopyRepository.findCopyByBookId(id);
        if (bookCopies == null || bookCopies.isEmpty()) {
            return Collections.emptyList(); // or throw an exception
        }
        return bookCopies;
    }

    public BookCopyEntity addBookCopy(Long bookId) {

        BookCopyEntity bookCopyEntity = new BookCopyEntity();
        bookCopyEntity.setBookId(bookId);
        bookCopyEntity.setAvailable(true); // Default to available

        try {
            return bookCopyRepository.save(bookCopyEntity);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error saving book copy: " + e.getMessage());
        }
    }

    public BookCopyEntity updateBookCopy(Long id, BookCopyEntity bookCopyEntity) {

            BookCopyEntity existingBookCopy = this.bookCopyRepository.findById(id).orElse(null);
            if (bookCopyEntity == null || existingBookCopy == null) {
                throw new IllegalArgumentException("Book copy or existing book copy not found");
            }

            // Update fields if they are not null
            if (bookCopyEntity.getAvailable() != null) {
                existingBookCopy.setAvailable(bookCopyEntity.getAvailable());
            }

            bookCopyRepository.save(existingBookCopy);

            return existingBookCopy;
    }

}
