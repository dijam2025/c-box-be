package dijam.c_box_be.signup.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id", unique = true, nullable = false, length = 20)
    private String userId;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "phone_number", nullable = false, length = 20) // camelCase 유지
    private String phoneNumber;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "role", nullable = false, length = 20) // 직책
    private String role;

    @Column(name = "created_at", nullable = false, length = 50)
    private String createdAt; // 정확한 이름 사용

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
