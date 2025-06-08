package dijam.c_box_be.rental.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentalRequestDto {
    private Long itemId;
    private String userId;
    private String item;
}
