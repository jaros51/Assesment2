package sk.wm.zadanie.zadanie2.DAO;

import jakarta.persistence.*;

@Entity
@Table(name="book_copy")
public class BookCopyEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "book_id", nullable = false, foreignKey = @ForeignKey(name = "fk_book_id"))
//    private BookEntity book;

    @Column(name="book_id", nullable=false)
    private Long bookId;

    @Column(name="available", nullable=false)
    private Boolean available;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

//    public BookEntity getBook() {
//        return book;
//    }
//    public void setBook(BookEntity book) {
//        this.book = book;
//    }

    public Boolean getAvailable() {
        return available;
    }
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Long getBookId() {
        return bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }


}
