package simple.mentoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.mentoring.domain.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
