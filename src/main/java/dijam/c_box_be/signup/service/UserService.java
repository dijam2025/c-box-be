package dijam.c_box_be.signup.service;

import dijam.c_box_be.signup.dto.UserDto;
import dijam.c_box_be.signup.entity.User;
import dijam.c_box_be.signup.repository.SignupRepository;
import org.springframework.transaction.annotation.Transactional;
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
        validateDuplicateUser(userDto);
        // DTO -> Entity 변환
        User user = User.builder()
                .userId(userDto.getUserId())
                .password(passwordEncoder.encode(userDto.getPassword())) // 비밀번호 암호화 저장
                .name(userDto.getName())
                .phoneNumber(userDto.getPhoneNumber())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .createdAt(userDto.getCreatedAt())
                .build();

        signupRepository.save(user);
    }

    private void validateDuplicateUser(UserDto userDto) {
        signupRepository.findByUserId(userDto.getUserId())
                .ifPresent(user -> { throw new IllegalArgumentException("이미 존재하는 ID 입니다."); });//중복 이름 확인

            signupRepository.findByPassword(userDto.getPassword())
                .ifPresent(user -> { throw new IllegalArgumentException("이미 사용 중인 비밀번호입니다."); }); //중복 전화번호 확인
    }

    @Transactional
    public void deleteUser(String userId) {
        User user = signupRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 회원이 존재하지 않습니다."));
        signupRepository.deleteByUserId(user.getUserId());
    }

}
