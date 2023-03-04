package simple.mentoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainForm() {
        return "mainForm";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }
}
