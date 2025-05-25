package sk.wm.zadanie.zadanie2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    @GetMapping("/view/books")
    public String getBooks() {

        ///  TODO !!!

        return "books"; // Thymeleaf template name
    }

}
