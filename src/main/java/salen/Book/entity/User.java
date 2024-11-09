package salen.Book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;
    @NotBlank(message = "Surname is mandatory")
    @Size(min = 2, max = 30, message = "Surname must be between 2 and 30 characters")
    private String surname;
    @NotBlank(message = "Email is mandatory")
    @Size(min = 2, max = 30, message = "Email must be between 2 and 30 characters")
    private String lastName;
    @NotNull(message = "birthday is mandatory")
    private LocalDate birthday;
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    public User(String name, String surname, String lastName, LocalDate birthday, String email) {
        this.name = name;
        this.surname = surname;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthday.getYear();
    }
}
