package dijam.c_box_be.signup.repository;

import dijam.c_box_be.signup.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SignupRepository extends JpaRepository<User, String> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByPassword(String password);
    @Transactional
    void deleteByUserId(String userId);
}
