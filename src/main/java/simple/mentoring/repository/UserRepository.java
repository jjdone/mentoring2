package simple.mentoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.mentoring.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginId(String loginId);
}
