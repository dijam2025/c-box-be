package dijam.c_box_be.mission.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String userId;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;

    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

