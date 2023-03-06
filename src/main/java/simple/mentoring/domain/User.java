package simple.mentoring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    @Column(unique = true, nullable = false)
    private String nickname;

    private Qualification qualification;
    private String phone;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @PrePersist
    public void createDate() {
        this.createdDate = LocalDateTime.now();
    }

    @Builder
    public User(String loginId, String password, String email, String nickname, Qualification qualification, String phone) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.qualification = qualification;
        this.phone = phone;
    }
}
