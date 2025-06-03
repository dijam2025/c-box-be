package dijam.c_box_be.mission.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "missions")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mission;

    private String userId;
    private String category;     // 예: 수업, 요청, 기타
    private String title;
    private String text;
    @Builder.Default
    private Integer comments = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

