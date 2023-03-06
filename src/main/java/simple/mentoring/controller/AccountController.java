package simple.mentoring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import simple.mentoring.dto.user.UserSignupDto;
import simple.mentoring.service.UserService;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserSignupDto userSignupDto) {
        userService.join(userSignupDto);
        return "redirect:/login";
    }
}
