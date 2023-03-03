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
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mentee_id", nullable = false)
    private User mentee;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false)
    private int rating;

    private String comment;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @PrePersist
    public void createDate() {
        this.createdDate = LocalDateTime.now();
    }

    @Builder
    public Review(User mentee, Post post, int rating, String comment) {
        this.mentee = mentee;
        this.post = post;
        this.rating = rating;
        this.comment = comment;
    }
}
