package dev.alexanderkg.budget_backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("items/{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable long id) {
        var item = itemService.readItem(id);
        var itemDto = new ItemDto(item.getName(), item.getPrice(), item.getCategory());
        return ResponseEntity.ok(itemDto);
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        return ResponseEntity.ok(itemService.readAllItems());
    }
}
