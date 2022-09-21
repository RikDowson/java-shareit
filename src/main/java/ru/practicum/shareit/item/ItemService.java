package ru.practicum.shareit.item;

import ru.practicum.shareit.item.model.Item;

import javax.validation.Valid;
import java.util.List;

public interface ItemService {

    List<Item> searchOwnerItem(Long userId);

    List<Item> searchItems(String text);

    Item getItemById(Long id);

    Item addItem(Long userId, @Valid Item item);

    Item updateItem(Long userId, Long id, Item newItem);

    void deleteItemById(Long userId, Long id);

}