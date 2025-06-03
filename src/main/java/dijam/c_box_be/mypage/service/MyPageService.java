package dijam.c_box_be.mypage.service;

import dijam.c_box_be.mypage.dto.MyPageRentalDto;
import dijam.c_box_be.signup.entity.User;
import dijam.c_box_be.signup.repository.SignupRepository;
import dijam.c_box_be.rental.repository.RentalHistoryRepository;
import dijam.c_box_be.rental.entity.RentalHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final SignupRepository signupRepository;
    private final RentalHistoryRepository historyRepository;

    public String getUserName(String userId) {
        User user = signupRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return user.getName();
    }

    public List<MyPageRentalDto> getMyPageRentalList(String userId) {
        return historyRepository.findByUserIdAndReturnedAtIsNull(userId).stream()
                .map(h -> {
                    LocalDateTime rentedAt = h.getRentedAt();
                    LocalDateTime dueDate = rentedAt.plusDays(7);
                    LocalDateTime returnedAt = h.getReturnedAt();
                    long daysLeft = Duration.between(LocalDateTime.now(), dueDate).toDays();

                    String status = (returnedAt != null)
                            ? "반납 완료" + (dueDate.isBefore(returnedAt) ? " (기한 초과)" : "")
                            : (daysLeft < 0 ? "반납 기한 초과" : "반납까지 " + daysLeft + "일 남음");

                    return new MyPageRentalDto(
                            h.getItem().getItemId(),
                            h.getItem().getItem(),
                            rentedAt,
                            dueDate,
                            h.getReturnedAt(),
                            Math.max(daysLeft, 0),
                            status
                    );
                }).collect(Collectors.toList());
    }
}
