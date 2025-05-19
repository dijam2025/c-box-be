package dijam.c_box_be.rental.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalRequestDto {
    private String userId;
    private Long itemId;
    private String item;
}
