package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getOwnerItems(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.searchOwnerItem(userId)
                .stream()
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<ItemDto> searchItems(@RequestParam String text) {
        return itemService.searchItems(text)
                .stream()
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ItemDto getItemById(@PathVariable Long id) {
        return ItemMapper.toItemDto(itemService.getItemById(id));
    }

    @PostMapping
    public ItemDto addItem(@RequestHeader("X-Sharer-User-Id") Long userId,
                           @Validated(Create.class) @RequestBody ItemDto itemDto) {
        Item item = ItemMapper.toItem(itemDto);
        return ItemMapper.toItemDto(itemService.addItem(userId, item));
    }

    @PatchMapping("/{id}")
    public ItemDto updateItem(@RequestHeader("X-Sharer-User-Id") Long userId,
                              @PathVariable Long id, @Validated(Update.class) @RequestBody ItemDto itemDto) {
        Item newItem = ItemMapper.toItem(itemDto);
        return ItemMapper.toItemDto(itemService.updateItem(userId, id, newItem));
    }

    @DeleteMapping("/{id}")
    public void deleteItemById(@RequestHeader("X-Sharer-User-Id") Long userId,
                               @PathVariable Long id) {
        itemService.deleteItemById(userId, id);
    }
}
