package rail.estore.storeservice.dto;


import java.math.BigDecimal;

public record RequestItemDto(
        String name,

        String description,

        Integer quantity,

        BigDecimal price

) {
}
