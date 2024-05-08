package rail.estore.storeservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rail.estore.storeservice.dto.RequestItemDto;
import rail.estore.storeservice.dto.ResponseItemDto;
import rail.estore.storeservice.exception.AlreadyExistsException;
import rail.estore.storeservice.exception.NotFoundException;
import rail.estore.storeservice.mapper.ItemMapper;
import rail.estore.storeservice.model.Item;
import rail.estore.storeservice.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public List<ResponseItemDto> getItems() {
        return itemRepository.findAll().stream().map(itemMapper::mapToDto).toList();
    }

    public ResponseItemDto findById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found"));
        return itemMapper.mapToDto(item);
    }

    @Transactional
    public ResponseItemDto save(RequestItemDto requestItemDto) {
        itemRepository.findByName(requestItemDto.name())
                .ifPresent(item -> {
                    throw new AlreadyExistsException(item.getName() + "already exists");
                });

        Item item = itemMapper.mapToObject(requestItemDto);
        Item savedItem = itemRepository.save(item);

        return itemMapper.mapToDto(savedItem);
    }

    @Transactional
    public ResponseItemDto update(Long id, RequestItemDto requestItemDto) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new NotFoundException("Item not found"));

        item.setName(requestItemDto.name());
        item.setDescription(requestItemDto.description());
        item.setQuantity(requestItemDto.quantity());
        item.setPrice(requestItemDto.price());

        Item updatedItem = itemRepository.save(item);
        return itemMapper.mapToDto(updatedItem);
    }

    @Transactional
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

}
