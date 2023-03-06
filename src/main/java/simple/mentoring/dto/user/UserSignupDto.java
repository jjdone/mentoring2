package simple.mentoring.dto.user;

import lombok.Data;
import simple.mentoring.domain.Qualification;

@Data
public class UserSignupDto {

    private String loginId;

    private String password;

    private String email;

    private String nickname;

    private String phone;

    private Qualification qualification;
}
