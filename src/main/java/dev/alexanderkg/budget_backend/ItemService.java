package dev.alexanderkg.budget_backend;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Long saveItem(Item item) {
        return itemRepository.save(item).getId();
    }

    public Item readItem(long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("Item: %d not found.", id)));
    }

    public List<ItemDto> readAllItems() {
        var itemsIterable = itemRepository.findAll();
        var itemDtos = new ArrayList<ItemDto>();
        itemsIterable.forEach(item -> {
            var itemDto = new ItemDto(item.getName(), item.getPrice(), item.getCategory());
            itemDtos.add(itemDto);
        });
        return itemDtos;
    }
}
