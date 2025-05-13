package dijam.c_box_be.signup.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "User")
public class User {
    @Id
    @Column(name = "UserId", unique = true, nullable = false, length = 20)
    private String userId;

    @Column(name="Password", nullable = false, length = 255)
    private String password;

    @Column(name="Name", nullable = false, length = 50)
    private String name;

    @Column(name = "PhoneNumber", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "Email", nullable = false, length = 30)
    private String email;

    @Column(name = "Role", nullable = false, length = 20) // 직책
    private String role;

    @Column(name = "CreatAt", nullable = false, length = 20)
    private String creatAt;
}
