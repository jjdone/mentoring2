package simple.mentoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.mentoring.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
