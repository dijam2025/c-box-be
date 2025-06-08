package dijam.c_box_be.login.controller;

import dijam.c_box_be.config.JwtUtil;
import dijam.c_box_be.login.dto.LoginRequest;
import dijam.c_box_be.login.service.LoginService;
import dijam.c_box_be.signup.entity.User;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginController {

    private final LoginService loginService;
    private final JwtUtil jwtUtil;

    public LoginController(LoginService loginService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        try {
            User user = loginService.authenticate(request.getUserId(), request.getPassword());
            String token = jwtUtil.generateToken(user.getUserId());

            return ResponseEntity.ok(Map.of(
                    "message", "로그인 성공",
                    "token", token,
                    "userId", user.getUserId(),
                    "name", user.getName(),
                    "role", user.getRole()
            ));
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            return ResponseEntity.status(401).body(Map.of("message", e.getMessage()));

        }
    }







}
