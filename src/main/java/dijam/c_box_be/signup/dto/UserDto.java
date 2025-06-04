package dijam.c_box_be.signup.dto;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserDto {
    private String userId;
    private String password;
    private String name;
    private String phoneNumber;
    private String email;
    private String role;
    private String createdAt;
}
