package salen.Book.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Data
@ToString(exclude = "comments")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull(message = "Name is mandatory")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;
    private String description;
    @NotNull
    @Size(min = 1, max = 50, message = "List of genres must be between 1 and 50")
    @CollectionTable(name = "books_genres", joinColumns = @JoinColumn(name = "book_id"))
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;
    @NotNull(message = "Author is mandatory")
    private Long authorId;
    private Long views = 0L;

    public Book(Long authorId, List<Genre> genres, String description, String name) {
        this.authorId = authorId;
        this.genres = genres;
        this.description = description;
        this.name = name;
    }

}

