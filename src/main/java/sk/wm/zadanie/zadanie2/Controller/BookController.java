package sk.wm.zadanie.zadanie2.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.wm.zadanie.zadanie2.DAO.BookCopyEntity;
import sk.wm.zadanie.zadanie2.DAO.BookEntity;
import sk.wm.zadanie.zadanie2.DTO.BookCopy;
import sk.wm.zadanie.zadanie2.DTO.BookEntityExt;
import sk.wm.zadanie.zadanie2.DTO.ErrorMessage;
import sk.wm.zadanie.zadanie2.Exceptions.InvalidIsbnException;
import sk.wm.zadanie.zadanie2.Service.BookService;
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

    @Operation(summary = "Get all books sorted and paginated")
    @GetMapping(value = "/pageable")
    ResponseEntity<Page<BookEntity>>getBooksSrt(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String sortBy,
                                             @RequestParam(defaultValue = "asc") String sortDir) {
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ?
                Sort.Direction.DESC : Sort.Direction.ASC;
        Page<BookEntity> books = bookService.getBooks(page, size, sortBy, direction);
        return  ResponseEntity.ok(books);
    }

    @Operation(summary = "Create a new book")
    @PostMapping(produces = "application/json" )
    ResponseEntity<Object> createBook(@RequestBody BookEntity book) { // TODO BookDto bookDto
        try {
            BookEntity bookAdded = this.bookService.createBook(book);
            return ResponseEntity.ok(bookAdded);
        } catch (InvalidIsbnException | DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage()));
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(500).body(new ErrorMessage("An error occurred while creating the book."));
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
    ResponseEntity<Object> updateBook(@PathVariable Long id, @RequestBody BookEntity book) {
        try {
            BookEntity updatedBook = this.bookService.updateBook(book, id);
            return ResponseEntity.ok(updatedBook);
        } catch (InvalidIsbnException | DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage()));
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(500).body(new ErrorMessage("An error occurred while updating the book."));
        }


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
