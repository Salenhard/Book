package salen.Book.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import salen.Book.entity.Book;
import salen.Book.entity.model.BookModelAssembler;
import salen.Book.exception.BookNotFoundException;
import salen.Book.service.BookService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService service;
    private final BookModelAssembler assembler;

    @GetMapping("/{id}")
    public EntityModel<Book> getBook(@PathVariable Long id) {
        Book book = service.get(id).orElseThrow(() -> new BookNotFoundException(id));
        service.increaseViews(book);
        return assembler.toModel(book);
    }

    @GetMapping("")
    public CollectionModel<EntityModel<Book>> getBooks() {
        List<EntityModel<Book>> books = service.getAll().stream().map(assembler::toModel).toList();
        return CollectionModel.of(books);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public CollectionModel<EntityModel<Book>> getBooksByName(@RequestParam(required = false) String name) {
        List<EntityModel<Book>> books = service.getAllByName(name).stream().map(assembler::toModel).toList();
        return CollectionModel.of(books);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @Valid @RequestBody Book newBook) {
        // обновление книги
        Book updatedBook = service.get(id).map(book -> {
            book.setName(newBook.getName());
            book.setAuthorId(newBook.getAuthorId());
            book.setGenres(newBook.getGenres());
            return service.update(book, 1);
        }).orElseThrow(() -> new BookNotFoundException(id));
        // преобразование книги в модель
        EntityModel<Book> model = assembler.toModel(updatedBook);
        // возвращение модели
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
    }

    @PostMapping("")
    public ResponseEntity<?> addBook(@RequestBody @Valid Book newBook) {
        // преобразование книги в модель
        EntityModel<Book> model = assembler.toModel(service.add(newBook, 1));
        // возвращение модели
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
    }
}
