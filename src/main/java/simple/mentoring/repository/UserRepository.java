package simple.mentoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.mentoring.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    boolean existsByEmail(String email);
    boolean existsByLoginId(String loginId);
    boolean existsByNickname(String nickname);
}
