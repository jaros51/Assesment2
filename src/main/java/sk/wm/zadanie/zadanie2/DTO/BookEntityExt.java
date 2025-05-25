package sk.wm.zadanie.zadanie2.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class BookEntityExt {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Integer publishedYear;
    private List<BookCopy> copies;

    public void setAuthor(String author) {
        this.author = author;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }
    public void setCopies(List<BookCopy> copies) {
        this.copies = copies;
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getIsbn() {
        return isbn;
    }
    public Integer getPublishedYear() {
        return publishedYear;
    }
    public List<BookCopy> getCopies() {
        return copies;
    }
}
