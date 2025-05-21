package dijam.c_box_be.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RentalStatusDto {
    private Long itemId;
    private String userId;
    private int daysLeft;
    private String statusMessage;
}
