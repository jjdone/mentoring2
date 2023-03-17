package simple.mentoring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simple.mentoring.domain.User;
import simple.mentoring.dto.user.UserDto;
import simple.mentoring.dto.user.UserSignupDto;
import simple.mentoring.dto.user.UserUpdateDto;
import simple.mentoring.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public Long signup(UserSignupDto userSignupDto) {

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

    @Transactional(readOnly = true)
    public UserDto findById(Long userId) {
        return new UserDto(userRepository.findById(userId).get());
    }

    @Transactional(readOnly = true)
    public UserUpdateDto getUserUpdateDto(Long userId) {
        return new UserUpdateDto(userRepository.findById(userId).get());
    }

    @Transactional
    public Long update(Long userId, UserUpdateDto userUpdateDto) {
        User findUser = userRepository.findById(userId).get();
        findUser.update(userUpdateDto);
        return findUser.getId();
    }
}
