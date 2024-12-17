package dev.alexanderkg.budget_backend;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemRepositoryDto saveItem(Item item) {
        var result = itemRepository.save(item);
        return new ItemRepositoryDto(result.getId(), result.getName(), result.getPrice(), result.getCategory());
    }

    public Item readItem(long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("Item: %d not found.", id)));
    }

    public List<CreateItemRequestBody> readAllItems() {
        var itemsIterable = itemRepository.findAll();
        var itemDtos = new ArrayList<CreateItemRequestBody>();
        itemsIterable.forEach(item -> {
            var itemDto = new CreateItemRequestBody(item.getName(), item.getPrice(), item.getCategory());
            itemDtos.add(itemDto);
        });
        return itemDtos;
    }
}
