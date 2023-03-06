package simple.mentoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import simple.mentoring.domain.Qualification;
import simple.mentoring.dto.user.UserLoginDto;
import simple.mentoring.dto.user.UserSignupDto;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainForm() {
        return "mainForm";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("form", new UserLoginDto());
        return "loginForm";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("form", new UserSignupDto());
        return "signupForm";
    }

    @ModelAttribute("qualification")
    public Qualification[] qualifications() {
        return Qualification.values();
    }
}
