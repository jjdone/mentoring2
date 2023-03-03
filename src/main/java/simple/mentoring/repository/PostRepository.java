package simple.mentoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.mentoring.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
