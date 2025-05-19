package dijam.c_box_be.rental.repository;

import dijam.c_box_be.rental.entity.RentalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentalHistoryRepository extends JpaRepository<RentalHistory, Long> {
    Optional<RentalHistory> findByUserIdAndItemIdAndReturnedAtIsNull(String userId, Long itemId);
}
