package rail.estore.storeservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rail.estore.storeservice.dto.RequestItemDto;
import rail.estore.storeservice.dto.ResponseItemDto;
import rail.estore.storeservice.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("v1/api/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseItemDto createItem(@RequestBody RequestItemDto dto) {
        return itemService.save(dto);
    }

    @GetMapping
    public List<ResponseItemDto> getAllItems() {
        return itemService.getItems();
    }

    @GetMapping("/{id}")
    public ResponseItemDto getItemById(@PathVariable Long id) {
        return itemService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseItemDto updateItemById(@PathVariable Long id, @RequestBody RequestItemDto dto) {
        return itemService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable Long id) {
        itemService.delete(id);
    }
}
