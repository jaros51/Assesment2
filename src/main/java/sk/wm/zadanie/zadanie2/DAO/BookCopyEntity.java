package sk.wm.zadanie.zadanie2.DAO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="book_copy")
@Getter
@Setter
public class BookCopyEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="book_id", nullable=false)
    private Long bookId;

    @Column(name="available", nullable=false)
    private Boolean available;

//    public Long getId() {
//        return id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Boolean getAvailable() {
//        return available;
//    }
//    public void setAvailable(Boolean available) {
//        this.available = available;
//    }
//
//    public Long getBookId() {
//        return bookId;
//    }
//    public void setBookId(Long bookId) {
//        this.bookId = bookId;
//    }

}
