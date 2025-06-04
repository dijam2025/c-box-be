package dijam.c_box_be.mission.service;

import dijam.c_box_be.mission.dto.MissionRequestDto;
import dijam.c_box_be.mission.dto.MissionResponseDto;
import dijam.c_box_be.mission.entity.Mission;
import dijam.c_box_be.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public List<MissionResponseDto> getAllMissions() {
        return missionRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    public List<MissionResponseDto> getMissionsByUserId(String userId) {
        return missionRepository.findByUserId(userId, Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    public MissionResponseDto createMission(MissionRequestDto dto) {
        Mission mission = Mission.builder()
                .userId(dto.getUserId())
                .title(dto.getTitle())
                .category(dto.getCategory())
                .text(dto.getText())
                .createdAt(LocalDateTime.now())
                .comments(0)
                .build();
        return toDto(missionRepository.save(mission));
    }

    public MissionResponseDto getMissionById(Long mission) {
        Mission post = missionRepository.findByMission(mission).orElseThrow(() -> new RuntimeException("Post not found"));
        return toDto(post);
    }

    private MissionResponseDto toDto(Mission mission) {
        return new MissionResponseDto(
                mission.getMission(),
                mission.getUserId(),
                mission.getCategory(),
                mission.getTitle(),
                mission.getText(),
                mission.getComments(),
                mission.getCreatedAt()
        );
    }
}
