package rail.estore.storeservice.mapper;

import org.mapstruct.Mapper;
import rail.estore.storeservice.dto.RequestItemDto;
import rail.estore.storeservice.dto.ResponseItemDto;
import rail.estore.storeservice.model.Item;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item mapToObject(RequestItemDto dto);

    ResponseItemDto mapToDto(Item item);
}
