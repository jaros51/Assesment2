package sk.wm.zadanie.zadanie2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sk.wm.zadanie.zadanie2.DAO.BookEntity;
import sk.wm.zadanie.zadanie2.DTO.BookCopy;

import java.util.List;
import java.util.Map;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

//    @Query(value = "SELECT c.id, CASE WHEN c.available = 1 THEN true ELSE false END as available FROM book_copy c WHERE c.book_id = ?1", nativeQuery = true)
//    List<BookCopy> findCopyByBookId(Long id);

//    @Query("SELECT c.id, CASE WHEN c.available = 1 THEN true ELSE false END FROM BookCopyEntity c WHERE c.bookId = ?1")
//    List<BookCopy> findCopyByBookId(Long id);

}
