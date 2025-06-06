package dijam.c_box_be.rental.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalRequestDto {
    private Long itemId;
    private String userId;
    private String item;
}
