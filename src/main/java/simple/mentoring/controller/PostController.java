package simple.mentoring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import simple.mentoring.dto.post.PostDto;
import simple.mentoring.dto.user.UserSessionDto;
import simple.mentoring.service.PostService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String mainForm(Model model) {
        List<PostDto> postDtoList = postService.findPostDtoList();
        model.addAttribute("posts", postDtoList);

        return "mainForm";
    }

    @GetMapping("/posts/uploadForm")
    public String uploadForm(Model model) {
        model.addAttribute("form", new PostDto());
        return "posts/uploadForm";
    }
}
