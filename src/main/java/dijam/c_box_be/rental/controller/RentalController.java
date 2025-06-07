package dijam.c_box_be.rental.controller;

import dijam.c_box_be.rental.dto.RentalRequestDto;
import dijam.c_box_be.rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rental")
@CrossOrigin(origins = "http://localhost:8080")
public class RentalController {

    private final RentalService rentalService;

    // 대여
    @PostMapping(value = "/rent", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> rent(@RequestBody RentalRequestDto dto) {
        String userId = getCurrentUserId();
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        rentalService.rentItem(userId, dto.getItemId());
        return ResponseEntity.ok("대여 완료");
    }

    // 반납
    @PostMapping(value = "/return", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> returnItem(@RequestBody RentalRequestDto dto) {
        String userId = getCurrentUserId();
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        rentalService.returnItem(userId, dto.getItemId());
        return ResponseEntity.ok("반납 완료");
    }

    // 현재 JWT로부터 userId 추출
    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return authentication.getName(); // JWT 필터에서 userId를 Principal로 세팅했다고 가정
    }
}
