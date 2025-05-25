package sk.wm.zadanie.zadanie2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sk.wm.zadanie.zadanie2.DAO.BookCopyEntity;
import sk.wm.zadanie.zadanie2.DTO.BookCopy;

import java.util.List;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopyEntity, Long> {

    @Query(value = "SELECT new sk.wm.zadanie.zadanie2.DTO.BookCopy(c.id, c.available ) FROM BookCopyEntity c WHERE c.bookId = ?1")
    List<BookCopy> findCopyByBookId(Long id);

    @Query(value = "SELECT * FROM book_copy c WHERE c.book_id = ?1", nativeQuery = true)
    List<BookCopyEntity> findAllByBookId(Long bookId);


}
