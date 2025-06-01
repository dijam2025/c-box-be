package dijam.c_box_be.mission.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;     // 예: 수업, 요청, 기타
    private String title;
    private Integer comments = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

