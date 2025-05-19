package dijam.c_box_be.rental.controller;

import dijam.c_box_be.rental.dto.RentalRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rental")
public class RentalController {

    private final RentalService rentalService;

    @PostMapping("/rent")
    public ResponseEntity<Stirng> rent(@RequestBody RentalRequestDto dto) {
        rentalService.rentItem(dto);
        return ResponseEntity.ok("대여 완료");
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnItem(@RequestBody RentalRequestDto dto) {
        rentalService.returnItem(dto);
        return ResponseEntity.ok("반납 완료");
    }
}

