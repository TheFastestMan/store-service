package rail.estore.storeservice.dto;


import java.math.BigDecimal;

public record ResponseItemDto(
        Long id,

        String name,

        String description,

        Integer quantity,

        BigDecimal price
) {
}
