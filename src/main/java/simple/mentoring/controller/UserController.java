package simple.mentoring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import simple.mentoring.domain.Qualification;
import simple.mentoring.dto.user.UserDto;
import simple.mentoring.dto.user.UserLoginDto;
import simple.mentoring.dto.user.UserSignupDto;
import simple.mentoring.dto.user.UserUpdateDto;
import simple.mentoring.service.UserService;


@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("form", new UserSignupDto());
        return "signupForm";
    }

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute("form") UserSignupDto userSignupDto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            log.info("errors={}", result);
            model.addAttribute("form", userSignupDto);
            return "signupForm";
        }

        userService.signup(userSignupDto);
        return "redirect:/login";
    }

    @ModelAttribute("qualification")
    public Qualification[] qualifications() {
        return Qualification.values();
    }

    @GetMapping("/login")
    public String loginForm(@RequestParam(required = false) String error,
                            @RequestParam(required = false) String exception, Model model) {
        model.addAttribute("form", new UserLoginDto());
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "loginForm";
    }

    @GetMapping("/users/{userId}")
    public String detailsForm(@PathVariable Long userId, Model model) {
        UserDto user = userService.findById(userId);
        model.addAttribute("user", user);
        return "users/DetailsForm";
    }

    @GetMapping("/users/{userId}/update")
    public String update(@PathVariable Long userId, Model model) {
        UserUpdateDto user = userService.getUserUpdateDto(userId);
        model.addAttribute("user", user);
        return "users/updateForm";
    }
}
