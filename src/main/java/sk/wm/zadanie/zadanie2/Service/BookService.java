package sk.wm.zadanie.zadanie2.Service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import sk.wm.zadanie.zadanie2.DAO.BookCopyEntity;
import sk.wm.zadanie.zadanie2.DAO.BookEntity;
import sk.wm.zadanie.zadanie2.DTO.BookCopy;
import sk.wm.zadanie.zadanie2.DTO.BookEntityExt;
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

    /***
     * Method to get book by id
     * @param id
     * @return
     * @throws IllegalArgumentException
     */
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

    public BookEntity createBook (BookEntity bookEntity) {
        // Logic to create a book
        try {
            // This will trigger setIsbn validation
            bookEntity.setIsbn(bookEntity.getIsbn());
            return this.bookRepository.save(bookEntity);
        } catch (DataIntegrityViolationException e) {
            // Handle other exceptions, such as database errors
            throw new RuntimeException("Error saving book: " + e.getMessage());
        }
    }

    public BookEntity updateBook (BookEntity bookEntity, Long id) {
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
            BookCopyEntity newCopy = bookCopyRepository.save(bookCopyEntity);
            return newCopy;
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
