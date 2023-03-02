package simple.mentoring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String writer;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @PrePersist
    public void createDate() {
        this.createdDate = LocalDateTime.now();
    }

    @Builder
    public Post(User user, String title, String contents, String writer) {
        this.user = user;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
    }
}
