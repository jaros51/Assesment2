package sk.wm.zadanie.zadanie2.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.wm.zadanie.zadanie2.DAO.BookCopyEntity;
import sk.wm.zadanie.zadanie2.DAO.BookEntity;
import sk.wm.zadanie.zadanie2.DTO.BookCopy;
import sk.wm.zadanie.zadanie2.DTO.BookEntityExt;
import sk.wm.zadanie.zadanie2.Exceptions.InvalidIsbnException;
import sk.wm.zadanie.zadanie2.Service.BookService;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Get all books")
    @GetMapping
    ResponseEntity<List<BookEntity>>getBooks() {
        List <BookEntity> books = bookService.getBooks();
        return  ResponseEntity.ok(books);
    }

    @Operation(summary = "Create a new book")
    @PostMapping(produces = "application/json" )
    ResponseEntity<Object> createBook(@RequestBody BookEntity book) { // TODO BookDto bookDto
        try {
            BookEntity bookAdded = this.bookService.createBook(book);
            return ResponseEntity.ok(bookAdded);
        } catch (InvalidIsbnException e) {
            //throw new IllegalArgumentException("Invalid ISBN format: " + e.getMessage());
            return ResponseEntity.badRequest().body("Invalid ISBN format: " + e.getMessage());
        }
    }

    @Operation(summary = "Get book by ID")
    @GetMapping(value = "/{id}")
    ResponseEntity<BookEntityExt>getBook(@PathVariable Long id) {
        BookEntityExt book;
        try {
            book = this.bookService.getBookById(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body( new BookEntityExt());
        }
        return  ResponseEntity.ok(book);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<BookEntity> updateBook(@PathVariable Long id, @RequestBody BookEntity book) {
        BookEntity updatedBook = this.bookService.updateBook(book, id);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        this.bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/copies")
    ResponseEntity<List<BookCopy>> getBookCopies(@PathVariable Long id) {
        List<BookCopy> bookCopies = this.bookService.getBookCopies(id);
        if (bookCopies.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookCopies);
    }

    @PostMapping(value = "/{id}/copies")
    ResponseEntity<BookCopyEntity> addBookCopy(@PathVariable Long id) {
        try {
            BookCopyEntity bookCopyEntity = this.bookService.addBookCopy(id);
            return ResponseEntity.ok(bookCopyEntity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping(value = "/{id}/copies/{copyId}")
    ResponseEntity<BookCopyEntity> updateBookCopy(@PathVariable Long id, @PathVariable Long copyId, @RequestBody BookCopyEntity bookCopy) {
        try {
            BookCopyEntity bookCopyEntity = this.bookService.updateBookCopy(copyId, bookCopy);
            return ResponseEntity.ok(bookCopyEntity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
