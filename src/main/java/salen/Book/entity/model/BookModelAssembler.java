package salen.Book.entity.model;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import salen.Book.controller.BookController;
import salen.Book.entity.Book;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookModelAssembler implements RepresentationModelAssembler<Book, EntityModel<Book>> {
    @Override
    public EntityModel<Book> toModel(Book book) {
        return EntityModel.of(book,
                linkTo(methodOn(BookController.class).getBook(book.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).getBooks()).withRel("books"));
    }

    @Override
    public CollectionModel<EntityModel<Book>> toCollectionModel(Iterable<? extends Book> books) {
        return RepresentationModelAssembler.super.toCollectionModel(books);
    }
}
