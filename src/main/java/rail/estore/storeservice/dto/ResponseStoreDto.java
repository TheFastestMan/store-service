package rail.estore.storeservice.dto;

public record ResponseStoreDto(
        Long id,
        String name,
        String logo,
        String description
) {
}
