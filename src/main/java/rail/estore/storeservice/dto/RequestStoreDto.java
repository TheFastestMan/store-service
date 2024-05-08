package rail.estore.storeservice.dto;

import lombok.Builder;
import rail.estore.storeservice.model.Item;

import java.util.List;

@Builder
public record RequestStoreDto(
        Long id,
        String name,
        String logo,
        String description,
        List<Item> items
) {
}
