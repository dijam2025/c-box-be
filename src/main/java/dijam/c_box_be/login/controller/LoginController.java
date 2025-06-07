package dijam.c_box_be.login.controller;
import dijam.c_box_be.login.dto.LoginRequest;
import dijam.c_box_be.login.service.LoginService;
import dijam.c_box_be.signup.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:8080")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request, HttpSession session) {
        try {
            User user = loginService.authenticate(request.getUserId(), request.getPassword());
            session.setAttribute("user", user);

            // ✅ JSON으로 응답 구성
            Map<String, String> response = new HashMap<>();
            response.put("message", "로그인 성공");
            response.put("role", user.getRole()); // 예: "ADMIN"
            response.put("name", user.getName());

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", e.getMessage()));
        }
    }


    @PutMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // 세션 만료
        return ResponseEntity.ok("로그아웃 성공");
    }
}
