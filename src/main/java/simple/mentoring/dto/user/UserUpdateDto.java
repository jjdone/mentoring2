package simple.mentoring.dto.user;

import lombok.Data;
import simple.mentoring.domain.User;

@Data
public class UserUpdateDto {

    private Long id;
    private String password;
    private String nickname;
    private String phone;

    public UserUpdateDto(User user) {
        this.id = user.getId();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.phone = user.getPhone();
    }
}
