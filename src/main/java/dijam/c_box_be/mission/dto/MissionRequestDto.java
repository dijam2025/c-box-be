package dijam.c_box_be.mission.dto;

import lombok.*;

@Getter
@Setter
public class MissionRequestDto {
    private String userId;
    private String category;
    private String title;
    private Long text;
}
