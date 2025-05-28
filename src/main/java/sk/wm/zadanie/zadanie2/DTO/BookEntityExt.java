package sk.wm.zadanie.zadanie2.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookEntityExt {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Integer publishedYear;
    private List<BookCopy> copies;

}
