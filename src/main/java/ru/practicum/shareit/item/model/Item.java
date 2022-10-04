package ru.practicum.shareit.item.model;

import lombok.*;

//import ru.practicum.shareit.requests.model.ItemRequest;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;                // уникальный идентификатор вещи;
    @NotBlank
    private String name;            // краткое название;
    @NotBlank
    private String description;     // развёрнутое описание;
    @Column(name = "is_available")
    private Boolean available;      // статус о том, доступна или нет вещь для аренды;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;             // владелец вещи;
}

