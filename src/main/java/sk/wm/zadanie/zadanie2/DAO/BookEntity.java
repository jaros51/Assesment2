package sk.wm.zadanie.zadanie2.DAO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="book")
@Getter
@Setter
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

}
