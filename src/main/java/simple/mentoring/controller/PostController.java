package simple.mentoring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import simple.mentoring.dto.post.PostDto;
import simple.mentoring.service.PostService;

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

    @GetMapping("/posts/{postId}")
    public String detailsForm(@PathVariable Long postId, Model model) {
        PostDto findPost = postService.findById(postId);
        model.addAttribute("post", findPost);
        return "posts/detailsForm";
    }
}
