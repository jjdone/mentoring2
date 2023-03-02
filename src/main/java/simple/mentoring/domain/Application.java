package simple.mentoring.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import simple.mentoring.configuration.ApplicationState;

import javax.persistence.*;

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
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mentee_id", nullable = false)
    private User mentee;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    private ApplicationState state;
}
