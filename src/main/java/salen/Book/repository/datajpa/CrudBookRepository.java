package salen.Book.repository.datajpa;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salen.Book.entity.Book;

@Repository
@Primary
public interface CrudBookRepository extends JpaRepository<Book, Long> {
}
