package rail.estore.storeservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rail.estore.storeservice.dto.RequestStoreDto;
import rail.estore.storeservice.dto.ResponseStoreDto;
import rail.estore.storeservice.service.StoreService;

import java.util.List;

@RestController
@RequestMapping("v1/api/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseStoreDto createStore(@RequestBody RequestStoreDto dto) {
        return storeService.save(dto);
    }

    @GetMapping
    public List<ResponseStoreDto> getAllStores() {
       return storeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseStoreDto getStoreById(@PathVariable Long id) {
        return storeService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseStoreDto updateStoreById(@PathVariable Long id, @RequestBody RequestStoreDto dto) {
        return storeService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteStoreById(@PathVariable Long id) {
        storeService.delete(id);
    }
}
