package dijam.c_box_be.rental.service;

import dijam.c_box_be.rental.dto.RentalRequestDto;
import dijam.c_box_be.rental.entity.Item;
import dijam.c_box_be.rental.entity.RentalHistory;
import dijam.c_box_be.rental.repository.ItemRepository;
import dijam.c_box_be.rental.repository.RentalHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final ItemRepository itemRepository;
    private final RentalHistoryRepository historyRepository;

    public void rentItem(RentalRequestDto dto) {
        System.out.println("rentItem 진입: itemId = " + dto.getItemId());

        try {
            Item item = itemRepository.findById(dto.getItemId())
                    .orElseThrow(() -> new IllegalArgumentException("물품이 없습니다"));

            if (item.isRented()) {
                throw new IllegalStateException("이미 대여 중입니다");
            }

            item.setRented(true);
            itemRepository.save(item);

            RentalHistory history = new RentalHistory(dto.getUserId(), item, LocalDateTime.now(), null);
            historyRepository.save(history);

            System.out.println("대여 처리 완료");
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
            throw e;
        }
    }

    public void returnItem(RentalRequestDto dto) {
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new IllegalArgumentException("물품이 없습니다"));

        item.setRented(false);
        itemRepository.save(item);

        RentalHistory history = historyRepository
                .findByUserIdAndItemIdAndReturnedAtIsNull(dto.getUserId(), dto.getItemId())
                .orElseThrow(() -> new IllegalStateException("대여 기록이 없습니다."));

        history.setReturnedAt(LocalDateTime.now());
        historyRepository.save(history);
    }
}
