package rail.estore.storeservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private Integer quantity;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

}
