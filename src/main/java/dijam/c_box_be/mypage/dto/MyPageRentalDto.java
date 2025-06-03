package dijam.c_box_be.mypage.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyPageRentalDto {
    private Long itemId;
    private String item;
    private LocalDateTime rentedAt;
    private LocalDateTime dueDate;
    private LocalDateTime returnedAt;
    private long daysLeft;
    private String statusMessage;
}
