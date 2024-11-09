package salen.Book.entity;

import lombok.*;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
    private Long id;
    private String name;
    private String surname;
    private String lastName;
    private LocalDate birthday;
    private String email;

    public int getAge() {
        return LocalDate.now().getYear() - birthday.getYear();
    }
}
