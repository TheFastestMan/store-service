package rail.estore.storeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rail.estore.storeservice.model.Item;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByName(String name);
}
