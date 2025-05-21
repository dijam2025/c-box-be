package dijam.c_box_be.rental.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalHistoryId;

    private String userId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private LocalDateTime rentedAt;
    private LocalDateTime returnedAt;

    public RentalHistory(String userId, Item item, LocalDateTime rentedAt, LocalDateTime returnedAt) {
        this.userId = userId;
        this.item = item;
        this.rentedAt = rentedAt;
        this.returnedAt = returnedAt;
    }
}
