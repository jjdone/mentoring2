package simple.mentoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import simple.mentoring.domain.Qualification;
import simple.mentoring.dto.user.UserLoginDto;
import simple.mentoring.dto.user.UserSessionDto;
import simple.mentoring.dto.user.UserSignupDto;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainForm(HttpSession session, Model model) {
        UserSessionDto user = (UserSessionDto) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user.getNickname());
        }
        return "mainForm";
    }

    @GetMapping("/login")
    public String loginForm(@RequestParam(required = false) String error,
                            @RequestParam(required = false) String exception,  Model model) {
        model.addAttribute("form", new UserLoginDto());
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
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
