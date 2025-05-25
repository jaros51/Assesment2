package sk.wm.zadanie.zadanie2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import sk.wm.zadanie.zadanie2.DAO.BookEntity;


@Repository
//@EnableJpaRepositories//??
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    // make getBooks named JPA repository call


}
