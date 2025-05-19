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
    private Long itemId;

    private String userId;

    @ManyToOne
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
