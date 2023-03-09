package simple.mentoring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import simple.mentoring.domain.Qualification;
import simple.mentoring.dto.user.UserSignupDto;
import simple.mentoring.service.UserService;


@Controller
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute("form") UserSignupDto userSignupDto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            log.info("errors={}", result);
            model.addAttribute("form", userSignupDto);
            return "signupForm";
        }

        userService.join(userSignupDto);
        return "redirect:/login";
    }

    @ModelAttribute("qualification")
    public Qualification[] qualifications() {
        return Qualification.values();
    }
}
