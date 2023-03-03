package simple.mentoring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "apply_uk",
                        columnNames = {"mentee_id", "post_id"}
                )
        }
)
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mentee_id", nullable = false)
    private User mentee;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    private ApplicationState state;

    private LocalDateTime createdDate;

    @PrePersist
    public void createDate() {
        this.createdDate = LocalDateTime.now();
    }

    @Builder
    public Application(User mentee, Post post, ApplicationState state) {
        this.mentee = mentee;
        this.post = post;
        this.state = state;
    }
}
