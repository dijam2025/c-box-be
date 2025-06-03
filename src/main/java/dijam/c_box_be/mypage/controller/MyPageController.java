package dijam.c_box_be.mypage.controller;

import dijam.c_box_be.mypage.dto.MyPageRentalDto;
import dijam.c_box_be.mypage.dto.MyPageResponseDto;
import dijam.c_box_be.mypage.service.MyPageService;
import dijam.c_box_be.rental.service.RentalService;
import dijam.c_box_be.mission.dto.MissionResponseDto;
import dijam.c_box_be.mission.service.MissionService;
import dijam.c_box_be.signup.entity.User;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping
    public ResponseEntity<MyPageResponseDto> getMyPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String name = myPageService.getUserName(user.getUserId());
        return ResponseEntity.ok(new MyPageResponseDto(name));
    }

    @GetMapping("/rental")
    public ResponseEntity<List<MyPageRentalDto>> getRentalHistory(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(myPageService.getMyPageRentalList(user.getUserId()));
    }

    @GetMapping("/mission")
    public ResponseEntity<List<MissionResponseDto>> getUserMissions(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(missionService.getMissionsByUserId(user.getUserId()));
    }
}
