package dijam.c_box_be.mission.repository;

import dijam.c_box_be.mission.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByMission_Mission(Long missionId);
}
