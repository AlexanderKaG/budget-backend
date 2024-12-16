package dev.alexanderkg.budget_backend;

import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Long saveItem(Item item) {
        return itemRepository.save(item).getId();
    }
}
