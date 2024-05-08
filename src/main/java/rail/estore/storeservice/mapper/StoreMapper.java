package rail.estore.storeservice.mapper;

import org.mapstruct.Mapper;
import rail.estore.storeservice.dto.RequestStoreDto;
import rail.estore.storeservice.dto.ResponseStoreDto;
import rail.estore.storeservice.model.Store;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    Store mapToObject(RequestStoreDto dto);

    ResponseStoreDto mapToDto(Store store);
}
