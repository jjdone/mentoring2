package simple.mentoring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simple.mentoring.domain.User;
import simple.mentoring.dto.user.UserSignupDto;
import simple.mentoring.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User join(UserSignupDto userSignupDto) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = User.builder()
                .loginId(userSignupDto.getLoginId())
                .password(encoder.encode(userSignupDto.getPassword()))
                .email(userSignupDto.getEmail())
                .nickname(userSignupDto.getNickname())
                .phone(userSignupDto.getPhone())
                .qualification(userSignupDto.getQualification())
                .build();

        return userRepository.save(user);
    }


}
