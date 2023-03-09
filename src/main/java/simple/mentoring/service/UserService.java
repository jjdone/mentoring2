package simple.mentoring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simple.mentoring.dto.user.UserSignupDto;
import simple.mentoring.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public Long join(UserSignupDto userSignupDto) {

        validation(userRepository.existsByLoginId(userSignupDto.getLoginId()), "이미 존재하는 아이디입니다.");
        validation(userRepository.existsByEmail(userSignupDto.getEmail()), "이미 존재하는 이메일입니다.");
        validation(userRepository.existsByNickname(userSignupDto.getNickname()), "이미 존재하는 닉네임입니다.");

        userSignupDto.setPassword(encoder.encode(userSignupDto.getPassword()));

        return userRepository.save(userSignupDto.toEntity()).getId();
    }

    private void validation(boolean exist, String message) {
        if (exist) {
            throw new RuntimeException(message);
        }
    }
}
