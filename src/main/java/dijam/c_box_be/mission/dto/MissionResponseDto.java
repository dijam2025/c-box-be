package dijam.c_box_be.mission.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MissionResponseDto {
    private String userId;
    private String category;
    private String title;
    private int comments;
    private LocalDateTime createdAt;
}

