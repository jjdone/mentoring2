package simple.mentoring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import simple.mentoring.domain.Qualification;
import simple.mentoring.domain.Role;
import simple.mentoring.dto.user.UserSignupDto;
import simple.mentoring.repository.UserRepository;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    private UserSignupDto userSignupDto;

    @BeforeEach
    public void setUp() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        userSignupDto = UserSignupDto.builder()
                .loginId("testId")
                .password(encoder.encode("1234"))
                .email("test@email.com")
                .nickname("testName")
                .phone("01012345678")
                .qualification(Qualification.MENTEE)
                .build();
    }

    @Test
    public void joinTest() throws Exception {
        //given

        //when
        Long savedId = userService.signup(userSignupDto);
        //then
        assertThat(userRepository.findById(savedId).get().getLoginId()).isEqualTo(userSignupDto.getLoginId());
        assertThat(userRepository.findById(savedId).get().getNickname()).isEqualTo(userSignupDto.getNickname());
        assertThat(userRepository.findById(savedId).get().getQualification()).isEqualTo(userSignupDto.getQualification());
        assertThat(userRepository.findById(savedId).get().getRole()).isEqualTo(Role.USER);
    }
}