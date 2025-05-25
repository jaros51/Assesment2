package sk.wm.zadanie.zadanie2.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.wm.zadanie.zadanie2.DTO.BookDto;
import sk.wm.zadanie.zadanie2.Service.BookService;
import java.util.List;

@RestController
public class BookController {

    private BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/books")
    ResponseEntity<List<BookDto>>getBooks() {
        // Logic to get books
        List <BookDto> books = bookService.getBooks();
        return  ResponseEntity.ok(books);
    }

//    @GetMapping(value = "/api/books/{id}")
//    ResponseEntity<BookDto>getBook(String id) {
//        // Logic to get books
//        BookDto book = bookService.getBookById(Long.getLong(id));
//        return  ResponseEntity.ok(book);
//    }
}
