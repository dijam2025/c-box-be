package dijam.c_box_be.mission.dto;

import lombok.*;

@Getter
@Setter
public class CommentRequestDto {
    private Long missionId;
    private String userId;
    private String content;
}
