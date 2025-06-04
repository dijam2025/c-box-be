package dijam.c_box_be.mission.service;

import dijam.c_box_be.mission.dto.*;
import dijam.c_box_be.mission.entity.*;
import dijam.c_box_be.mission.repository.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MissionRepository missionRepository;

    public CommentResponseDto createComment(CommentRequestDto dto) {
        Mission mission = missionRepository.findByMission(dto.getMissionId())
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        mission.setComments(mission.getComments() + 1);
        missionRepository.save(mission);

        Comment comment = Comment.builder()
                .userId(dto.getUserId())
                .content(dto.getContent())
                .mission(mission)
                .createdAt(LocalDateTime.now())
                .build();

        return toDto(commentRepository.save(comment));
    }

    public List<CommentResponseDto> getCommentsByMission(Long missionId) {
        return commentRepository.findByMission_Mission(missionId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(
                comment.getCommentId(),
                comment.getUserId(),
                comment.getContent(),
                comment.getCreatedAt()
        );
    }
}

