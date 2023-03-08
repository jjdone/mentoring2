package simple.mentoring.config.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import simple.mentoring.config.auth.PrincipalDetails;
import simple.mentoring.config.oauth.provider.FacebookUserInfo;
import simple.mentoring.config.oauth.provider.GoogleUserInfo;
import simple.mentoring.config.oauth.provider.NaverUserinfo;
import simple.mentoring.config.oauth.provider.OAuth2UserInfo;
import simple.mentoring.domain.Qualification;
import simple.mentoring.domain.User;
import simple.mentoring.repository.UserRepository;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String userClientId = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        return getPrincipalDetails(attributes, getOAuth2UserInfo(userClientId, attributes));
    }

    private static OAuth2UserInfo getOAuth2UserInfo(String userClientId, Map<String, Object> attributes) {
        OAuth2UserInfo oAuth2UserInfo = null;

        if (userClientId.equals("google")) {
            oAuth2UserInfo = new GoogleUserInfo(attributes);
        } else if (userClientId.equals("facebook")) {
            oAuth2UserInfo = new FacebookUserInfo(attributes);
        } else if (userClientId.equals("naver")) {
            oAuth2UserInfo = new NaverUserinfo((Map<String, Object>) attributes.get("response"));
        } else {
            log.info("OAuth 로그인 실패");
        }
        return oAuth2UserInfo;
    }

    private PrincipalDetails getPrincipalDetails(Map<String, Object> attributes, OAuth2UserInfo oAuth2UserInfo) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User findUser = userRepository.findByEmail(oAuth2UserInfo.getEmail());

        if (findUser == null) {
            User user = User.builder()
                    .loginId(oAuth2UserInfo.getProviderId())
                    .password(encoder.encode(UUID.randomUUID().toString()))
                    .email(oAuth2UserInfo.getEmail())
                    .nickname(oAuth2UserInfo.getProvider() + oAuth2UserInfo.getProviderId())
                    .qualification(Qualification.MENTEE)
                    .phone(null)
                    .build();

            return new PrincipalDetails(userRepository.save(user), attributes);
        }

        return new PrincipalDetails(findUser);
    }
}
