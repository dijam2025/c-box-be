package dijam.c_box_be.rental.service;
import dijam.c_box_be.rental.dto.RentalRequestDto;
import dijam.c_box_be.rental.entity.*;
import dijam.c_box_be.rental.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final ItemRepository itemRepository;
    private final RentalHistoryRepository historyRepository;

    public void rentItem(String userId, Long itemId) {
        System.out.println("rentItem 진입: itemId = " + itemId);

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("물품이 없습니다"));

        if (item.isRented()) {
            throw new IllegalStateException("이미 대여 중입니다");
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

        history.setReturnedAt(LocalDateTime.now());
        historyRepository.save(history);
    }
}
