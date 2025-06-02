package dijam.c_box_be.mission.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MissionResponseDto {
    private Long mission;
    private String userId;
    private String category;
    private String title;
    private String text;
    private int comments;
    private LocalDateTime createdAt;
}

