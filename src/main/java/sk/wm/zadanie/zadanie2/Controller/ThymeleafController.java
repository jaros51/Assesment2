package sk.wm.zadanie.zadanie2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sk.wm.zadanie.zadanie2.DTO.BookEntityExt;
import sk.wm.zadanie.zadanie2.Service.BookService;

@Controller
public class ThymeleafController {

    private final BookService bookService;

    public ThymeleafController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/view/books")
    public String getBooks(Model model) {
        model.addAttribute("books", this.bookService.getBooks());
        return "books"; // Thymeleaf template name
    }

    @GetMapping("/view/books/{id}")
    public String getBookDetail(@PathVariable Long id, Model model) {
        BookEntityExt book = this.bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book-detail"; // Thymeleaf template name
    }

}
