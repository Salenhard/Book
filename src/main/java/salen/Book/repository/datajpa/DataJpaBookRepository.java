package salen.Book.repository.datajpa;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import salen.Book.entity.Book;
import salen.Book.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
@Profile("jpa")
public class DataJpaBookRepository implements BookRepository {
    private final CrudBookRepository crudBookRepository;
    @Override
    public Optional<Book> get(long id) {
        return crudBookRepository.findById(id);
    }

    @Override
    public List<Book> getAll() {
        return crudBookRepository.findAll();
    }

    @Override
    public Book save(Book book, long userId) {
        book.setAuthorId(userId);
        return crudBookRepository.save(book);
    }

    @Override
    public Book update(Book book, long userId) {
        return save(book, userId);
    }

    @Override
    public void delete(long id) {
        crudBookRepository.deleteById(id);
    }

    @Override
    public void increaseViews(Book book) {
        book.setViews(book.getViews() + 1);
        crudBookRepository.save(book);
    }


}
