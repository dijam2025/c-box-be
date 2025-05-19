package dijam.c_box_be.rental.repository;

import dijam.c_box_be.rental.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
