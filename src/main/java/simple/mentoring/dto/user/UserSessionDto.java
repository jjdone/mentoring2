package simple.mentoring.dto.user;

import lombok.Data;
import simple.mentoring.domain.Qualification;
import simple.mentoring.domain.Role;
import simple.mentoring.domain.User;

@Data
public class UserSessionDto {

    private String loginId;
    private String password;
    private String email;
    private String nickname;
    private Role role;
    private Qualification qualification;
    private String phone;

    public UserSessionDto(User user) {
        this.loginId = user.getLoginId();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.role = user.getRole();
        this.qualification = user.getQualification();
        this.phone = user.getPhone();
    }
}
