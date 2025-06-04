package dijam.c_box_be.signup.controller;

import dijam.c_box_be.signup.dto.UserDto;
import dijam.c_box_be.signup.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:8080")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(@RequestBody UserDto userDto) {
        try {
            userService.signup(userDto); // 서비스에 등록 요청l;
            return ResponseEntity.ok("회원가입 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("회원탈퇴 성공");
        } catch (Exception e) {
            e.printStackTrace(); // <<< 여기가 핵심! 전체 스택트레이스 출력
            return ResponseEntity.status(500).body("서버 에러 발생: " + e.getMessage());
        }
    }

}
