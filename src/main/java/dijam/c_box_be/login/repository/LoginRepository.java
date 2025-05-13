package dijam.c_box_be.login.repository;

import dijam.c_box_be.login.dto.LoginRequest;
import dijam.c_box_be.signup.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<User, String> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByPassword(String password);
}
