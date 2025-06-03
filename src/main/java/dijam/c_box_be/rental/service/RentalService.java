package dijam.c_box_be.rental.service;

import dijam.c_box_be.rental.entity.Item;
import dijam.c_box_be.rental.entity.RentalHistory;
import dijam.c_box_be.rental.repository.ItemRepository;
import dijam.c_box_be.rental.repository.RentalHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final ItemRepository itemRepository;
    private final RentalHistoryRepository historyRepository;

    public void rentItem(String userId, Long itemId) {
        System.out.println("rentItem 진입: itemId = " + itemId);

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("물품이 없습니다"));

        Optional<RentalHistory> existing = historyRepository
                .findByUserIdAndItem_ItemIdAndReturnedAtIsNull(userId, itemId);

        if (item.isRented()) {
            throw new IllegalStateException("이미 대여 중입니다");
        }
        if (existing.isPresent()) {
            throw new IllegalStateException("아직 반납하지 않은 같은 아이템이 있습니다.");
        }

        item.setRented(true);
        itemRepository.save(item);

        RentalHistory history = new RentalHistory(userId, item, LocalDateTime.now(), null);
        historyRepository.save(history);

        System.out.println("대여 처리 완료");
    }

    public void returnItem(String userId, Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("물품이 없습니다"));

        item.setRented(false);
        itemRepository.save(item);

        RentalHistory history = historyRepository
                .findByUserIdAndItem_ItemIdAndReturnedAtIsNull(userId, itemId)
                .orElseThrow(() -> new IllegalStateException("대여 기록이 없습니다."));

        LocalDateTime now = LocalDateTime.now();
        if (history.getRentedAt().plusDays(7).isBefore(now)) {
            throw new IllegalStateException("반납 기한(7일)이 초과되었습니다.");
        }

        history.setReturnedAt(now);
        historyRepository.save(history);
    }
}
