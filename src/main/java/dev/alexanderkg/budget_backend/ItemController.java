package dev.alexanderkg.budget_backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/items")
    public ResponseEntity<Void> createItem(@RequestBody ItemDto itemDto) {
        var item = new Item();
        item.setName(itemDto.name());
        item.setPrice(itemDto.price());
        item.setCategory(itemDto.category());
        var itemId = itemService.saveItem(item);
        return ResponseEntity.created(URI.create(String.format("/items/%s", itemId))).build();
    }
}
