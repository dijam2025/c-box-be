package dijam.c_box_be.mission.repository;

import dijam.c_box_be.mission.entity.Mission;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findByCategory(String category);
    List<Mission> findByUserId(String userId, Sort sort);


    Optional<Mission> findByMission(Long mission);
}

