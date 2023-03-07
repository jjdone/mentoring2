package simple.mentoring.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import simple.mentoring.domain.Qualification;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupDto {

    private String loginId;

    private String password;

    private String email;

    private String nickname;

    private String phone;

    private Qualification qualification;
}
