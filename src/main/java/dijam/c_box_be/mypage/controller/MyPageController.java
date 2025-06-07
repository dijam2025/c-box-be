package dijam.c_box_be.mypage.controller;

import dijam.c_box_be.config.JwtUtil;
import dijam.c_box_be.mypage.dto.MyPageRentalDto;
import dijam.c_box_be.mypage.dto.MyPageResponseDto;
import dijam.c_box_be.mypage.service.MyPageService;
import dijam.c_box_be.rental.service.RentalService;
import dijam.c_box_be.mission.dto.MissionResponseDto;
import dijam.c_box_be.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;
    private final RentalService rentalService;
    private final MissionService missionService;
    private final JwtUtil jwtUtil;

    private String extractUserIdFromToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }
        String token = authHeader.substring(7); // "Bearer " 이후 자름
        return jwtUtil.validateTokenAndGetUserId(token);
    }

    @GetMapping
    public ResponseEntity<MyPageResponseDto> getMyPage(@RequestHeader("Authorization") String authHeader) {
        try {
            String userId = extractUserIdFromToken(authHeader);
            String name = myPageService.getUserName(userId);
            return ResponseEntity.ok(new MyPageResponseDto(name));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/rental")
    public ResponseEntity<List<MyPageRentalDto>> getRentalHistory(@RequestHeader("Authorization") String authHeader) {
        try {
            String userId = extractUserIdFromToken(authHeader);
            return ResponseEntity.ok(myPageService.getMyPageRentalList(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/mission")
    public ResponseEntity<List<MissionResponseDto>> getUserMissions(@RequestHeader("Authorization") String authHeader) {
        try {
            String userId = extractUserIdFromToken(authHeader);
            return ResponseEntity.ok(missionService.getMissionsByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
