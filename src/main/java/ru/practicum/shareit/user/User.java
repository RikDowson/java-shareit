package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class User {
    private Long id;        // уникальный идентификатор пользователя;
    @NotNull
    @NotBlank
    private String name;    //  имя или логин пользователя;
    @NotNull
    @NotBlank
    @Email
    private String email;   // адрес электронной почты (учтите, что два пользователя не могут
                            // иметь одинаковый адрес электронной почты).
}