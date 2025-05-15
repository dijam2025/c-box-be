package dijam.c_box_be.login.service;

import dijam.c_box_be.login.repository.LoginRepository;
import dijam.c_box_be.signup.entity.User;
import dijam.c_box_be.signup.repository.SignupRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final SignupRepository signupRepository;

    public LoginService(BCryptPasswordEncoder passwordEncoder,
                        SignupRepository signupRepository) {
        this.passwordEncoder = passwordEncoder;
        this.signupRepository = signupRepository;
    }

    public User authenticate(String userId, String password) {
        User user = signupRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }
}
