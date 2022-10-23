package ru.practicum.shareit.request.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.ItemRequestMapper;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.request.repository.ItemRequestRepository;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemRequestServiceImpl implements ItemRequestService {

    private  final ItemRequestRepository itemRequestRepository;
    private final UserService userService;

    @Override
    public List<ItemRequestDto> getAllRequestsByOwnerId(Long ownerId) {

        userService.getUserById(ownerId);
        List<ItemRequestDto> requestList = itemRequestRepository.findAllByRequesterIdOrderByCreatedDesc(ownerId)
                .stream()
                .map(ItemRequestMapper::toItemRequestDto)
                .collect(Collectors.toList());

        return requestList;
    }

    @Override
    public ItemRequest createItemRequest(Long ownerId, ItemRequest itemRequest) {
        itemRequest.setRequester(userService.getUserById(ownerId));
        return itemRequestRepository.save(itemRequest);
    }

    @Override
    public List<ItemRequest> getAllRequestsByPage(Long ownerId, Integer from, Integer size) {
        userService.getUserById(ownerId);
        if (from < 0) {
            throw new ValidationException(String.format("Неверное значение 'from' %d.", from));
        }
        if (size < 1) {
            throw new ValidationException(String.format("Неверное значение 'size' %d.", size));
        }
        return itemRequestRepository.findAllByIdNot(ownerId,
                PageRequest.of(from, size, Sort.by(Sort.Direction.DESC, "created")));
    }

    @Override
    public ItemRequest getItemRequestById(Long ownerId, Long itemRequestId) {
        userService.getUserById(ownerId);
        return itemRequestRepository.findById(itemRequestId)
                .orElseThrow(() -> new NotFoundException("Введено некорректное значение id"));
    }
}
