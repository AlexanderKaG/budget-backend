package dev.alexanderkg.budget_backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/items")
    public ResponseEntity<ItemRepositoryDto> createItem(@RequestBody CreateItemRequestBody createItemRequestBody) {
        var item = new Item();
        item.setName(createItemRequestBody.name());
        item.setPrice(createItemRequestBody.price());
        item.setCategory(createItemRequestBody.category());
        var itemRepositoryDto = itemService.saveItem(item);
        return ResponseEntity.created(URI.create(String.format("/items/%s", itemRepositoryDto.id()))).body(itemRepositoryDto);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/items")
    public ResponseEntity<List<CreateItemRequestBody>> getAllItems() {
        return ResponseEntity.ok(itemService.readAllItems());
    }

    @GetMapping("items/{id}")
    public ResponseEntity<CreateItemRequestBody> getItem(@PathVariable long id) {
        var item = itemService.readItem(id);
        var itemDto = new CreateItemRequestBody(item.getName(), item.getPrice(), item.getCategory());
        return ResponseEntity.ok(itemDto);
    }
}
