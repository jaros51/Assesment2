package sk.wm.zadanie.zadanie2.DAO;

import jakarta.persistence.*;

@Entity
@Table(name="book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable=false, unique=true)
    private String title;

    @Column(name="author", nullable=false)
    private String author;

    @Column(name="isbn",nullable=false) ///  TODO ISBN format validation
    private String isbn;

    @Column(name="published_year",nullable=false) ///  TODO Validation of year
    private Integer publishedYear;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

}
