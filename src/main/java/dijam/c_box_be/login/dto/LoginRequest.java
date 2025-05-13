package dijam.c_box_be.login.dto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    private String userId;
    private String password;
}
