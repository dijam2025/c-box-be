package dijam.c_box_be.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RentalStatusDto {
    private Long itemId;
    private String item;
    private String userId;
    private String role;
    private LocalDateTime rentedAt;
    private LocalDateTime dueDate;
    private LocalDateTime returnedAt;
    private long daysLeft;
    private String statusMessage;
}
