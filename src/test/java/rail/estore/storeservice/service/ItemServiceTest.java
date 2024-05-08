package rail.estore.storeservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rail.estore.storeservice.dto.RequestItemDto;
import rail.estore.storeservice.dto.ResponseItemDto;
import rail.estore.storeservice.exception.AlreadyExistsException;
import rail.estore.storeservice.exception.NotFoundException;
import rail.estore.storeservice.mapper.ItemMapper;
import rail.estore.storeservice.model.Item;
import rail.estore.storeservice.repository.ItemRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    private static final Long ID = 1L;
    private static final String NAME = "item_name";

    @Mock
    ItemRepository itemRepository;
    @Mock
    ItemMapper itemMapper;

    @InjectMocks
    ItemService itemService;

    @Test
    void getItems() {
        Item item = new Item();
        ResponseItemDto responseItemDto = new ResponseItemDto(1L, "", "", 1, new BigDecimal("0.1"));
        List<Item> items = new ArrayList<>();
        items.add(item);

        List<ResponseItemDto> responseItemDtos = new ArrayList<>();
        responseItemDtos.add(responseItemDto);

        when(itemRepository.findAll()).thenReturn(items);
        when(itemMapper.mapToDto(item)).thenReturn(responseItemDto);

        List<ResponseItemDto> response = itemService.getItems();
        Assertions.assertEquals(response.get(0).name(), responseItemDtos.get(0).name());
    }

    @Test
    void shouldFindById() {
        Item item = new Item();
        ResponseItemDto responseItemDto = new ResponseItemDto(1L, "", "", 1, new BigDecimal("0.1"));

        when(itemRepository.findById(ID)).thenReturn(Optional.of(item));
        when(itemMapper.mapToDto(item)).thenReturn(responseItemDto);

        ResponseItemDto response = itemService.findById(ID);
        Assertions.assertEquals(responseItemDto.name(), response.name());
    }

    @Test
    void shouldNotFindById() {
        when(itemRepository.findById(ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> itemService.findById(ID));
    }

    @Test
    void shouldNotSave() {
        Item item = new Item();
        RequestItemDto requestItemDto = new RequestItemDto("item_name", "", 1, new BigDecimal("0.1"));

        when(itemRepository.findByName(NAME)).thenReturn(Optional.of(item));

        Assertions.assertThrows(AlreadyExistsException.class, () -> itemService.save(requestItemDto));
    }

    @Test
    void shouldSave() {
        Item item = new Item();
        item.setName(NAME);
        RequestItemDto requestItemDto = new RequestItemDto("item_name", "", 1, new BigDecimal("0.1"));
        ResponseItemDto responseItemDto = new ResponseItemDto(1L, "item_name", "", 1, new BigDecimal("0.1"));


        when(itemRepository.findByName(NAME)).thenReturn(Optional.empty());
        when(itemMapper.mapToObject(requestItemDto)).thenReturn(item);
        when(itemRepository.save(item)).thenReturn(item);
        when(itemMapper.mapToDto(item)).thenReturn(responseItemDto);

        ResponseItemDto response = itemService.save(requestItemDto);

        Assertions.assertEquals(response.name(), responseItemDto.name());
    }

    @Test
    void shouldUpdate() {
        Item item = new Item();
        item.setName(NAME);
        RequestItemDto updatedData = new RequestItemDto("update_name", "update_desc", 2, new BigDecimal("0.2"));
        ResponseItemDto responseItemDto = new ResponseItemDto(1L, "update_name", "update_desc", 2, new BigDecimal("0.2"));

        item.setName(updatedData.name());
        item.setDescription(updatedData.description());
        item.setQuantity(updatedData.quantity());
        item.setPrice(updatedData.price());

        when(itemRepository.findById(ID)).thenReturn(Optional.of(item));
        when(itemRepository.save(item)).thenReturn(item);
        when(itemMapper.mapToDto(item)).thenReturn(responseItemDto);

        ResponseItemDto response = itemService.update(ID,updatedData);

        Assertions.assertEquals(updatedData.name(), response.name());
    }

    @Test
    void shouldNotUpdate() {
        RequestItemDto updatedData = new RequestItemDto("update_name", "update_desc", 2, new BigDecimal("0.2"));

        when(itemRepository.findById(ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> itemService.update(ID,updatedData));
    }

    @Test
    void delete() {
        itemService.delete(ID);
        verify(itemRepository).deleteById(ID);
    }
}