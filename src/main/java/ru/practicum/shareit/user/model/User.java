package ru.practicum.shareit.user.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // уникальный идентификатор пользователя;

    private String name;        //  имя или логин пользователя;

    @Email
    private String email;       // адрес электронной почты (учтите, что два пользователя не могут
                                // иметь одинаковый адрес электронной почты).
}
