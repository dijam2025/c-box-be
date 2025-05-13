package dijam.c_box_be.signup.service;

import dijam.c_box_be.signup.dto.UserDto;
import dijam.c_box_be.signup.entity.User;
import dijam.c_box_be.signup.repository.SignupRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final SignupRepository signupRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(SignupRepository signupRepository, BCryptPasswordEncoder passwordEncoder) {
        this.signupRepository = signupRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(UserDto userDto) {
        // 유효성 검사 로직
        validateMandatoryFields(userDto);

        // DTO -> Entity 변환
        User user = User.builder()
                .userId(userDto.getUserId())
                .password(passwordEncoder.encode(userDto.getPassword())) // 비밀번호 암호화 저장
                .name(userDto.getName())
                .phoneNumber(userDto.getPhoneNumber())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .creatAt(userDto.getCreatAt())
                .build();

        signupRepository.save(user);
    }

    // 필수 입력 값 확인
    private void validateMandatoryFields(UserDto userDto) {
        if (userDto.getUserId() == null || userDto.getUserId().isEmpty()) {
            throw new IllegalArgumentException("userId는 필수 값입니다.");
        }
        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 필수 값입니다.");
        }
        // 다른 필드도 추가...
    }
}
