package sk.wm.zadanie.zadanie2.DTO;

import lombok.Data;

@Data
public class BookDto {

    private long id;
    private String title;
    private String author;
    private String isbn;
    private int publishedYear;
}
