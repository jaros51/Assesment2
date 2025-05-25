package sk.wm.zadanie.zadanie2.DAO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="book_entity")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookEntity {

    @Id
    private Long id;

    @Column(name="title", length=255, nullable=false, unique=true)
    private String title;

    @Column(name="author", length=255, nullable=false, unique=false)
    private String author;

    @Column(name="isbn", length=255, nullable=false, unique=false) ///  TODO ISBN format validation
    private String isbn;

    @Column(name="published_year", length=255, nullable=false, unique=false) ///  TODO Validation of year
    private Integer publishedYear;

//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getIsbn() {
//        return isbn;
//    }
//
//    public void setIsbn(String isbn) {
//        this.isbn = isbn;
//    }
//
//    public Integer getPublishedYear() {
//        return publishedYear;
//    }
//
//    public void setPublishedYear(Integer publishedYear) {
//        this.publishedYear = publishedYear;
//    }

}
