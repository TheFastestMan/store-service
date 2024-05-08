package rail.estore.storeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rail.estore.storeservice.model.Store;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByName(String name);
}
