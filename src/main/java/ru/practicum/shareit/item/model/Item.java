package ru.practicum.shareit.item.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * TODO Sprint add-controllers.
 */

@Data
@AllArgsConstructor
public class Item {
    private Long id;                // уникальный идентификатор вещи;
    @NotNull
    @NotBlank
    private String name;            // краткое название;
    @NotNull
    @NotBlank
    private String description;     // развёрнутое описание;
    private Boolean available;      // статус о том, доступна или нет вещь для аренды;
    private User owner;             // владелец вещи;
    private ItemRequest request;    // если вещь была создана по запросу другого пользователя, то в этом
                                    // поле будет храниться ссылка на соответствующий запрос.

}
