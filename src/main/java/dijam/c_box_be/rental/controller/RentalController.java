package dijam.c_box_be.rental.controller;

import dijam.c_box_be.rental.dto.*;
import dijam.c_box_be.rental.service.RentalService;
import dijam.c_box_be.signup.entity.User;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rental")
public class RentalController {

    private final RentalService rentalService;

    @PostMapping("/rent")
    public ResponseEntity<String> rent(@RequestBody RentalRequestDto dto, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }
        rentalService.rentItem(user.getUserId(), dto.getItemId());
        return ResponseEntity.ok("대여 완료");
    }


    @PostMapping("/return")
    public ResponseEntity<String> returnItem(@RequestBody RentalRequestDto dto, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }
        rentalService.returnItem(user.getUserId(), dto.getItemId());
        return ResponseEntity.ok("반납 완료");
    }
}

