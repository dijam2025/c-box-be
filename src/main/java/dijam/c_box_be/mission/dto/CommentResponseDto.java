package dijam.c_box_be.mission.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String userId;
    private String content;
    private LocalDateTime createdAt;
}
