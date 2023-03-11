package simple.mentoring.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import simple.mentoring.domain.Qualification;
import simple.mentoring.domain.Role;
import simple.mentoring.domain.User;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,16}$", message = "아이디는 영문 대, 소문자와 숫자 조합의 4 ~ 16자로 작성해주세요.")
    private String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{10,25}",
            message = "비밀번호는 영문 대, 소문자와 숫자 그리고 특수문자가 적어도 1개 이상씩 포함된 10 ~ 25자로 작성해주세요.")
    private String password;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    @Size(max = 255, message = "이메일은 최대 255자 입니다.")
    private String email;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Pattern(regexp = "^[0-9a-zA-Zㄱ-ㅎ가-힣]{2,16}$",
            message = "닉네임은 특수문자를 제외한 영문 대, 소문자와 숫자 그리고 한글 조합의 2 ~ 16자로 작성해주세요.")
    private String nickname;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "^*[0-9]{10,11}$", message = "전화번호는 숫자만 사용하여 10~11자로 작성해주세요.")
    private String phone;

    private Qualification qualification;

    public User toEntity() {
        return User.builder()
                .loginId(loginId)
                .password(password)
                .email(email)
                .nickname(nickname)
                .phone(phone)
                .qualification(qualification)
                .role(Role.ROLE_USER)
                .build();
    }
}
