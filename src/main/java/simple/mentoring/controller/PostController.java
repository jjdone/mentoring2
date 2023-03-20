package simple.mentoring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import simple.mentoring.dto.post.PostDto;
import simple.mentoring.dto.post.PostUpdateDto;
import simple.mentoring.service.PostService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String mainForm(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                           Model model) {
        model.addAttribute("posts", postService.pageList(pageable));
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

    @GetMapping("/posts/{postId}/update")
    public String updateForm(@PathVariable Long postId, Model model) {
        PostUpdateDto findPost = postService.getPostUpdateDto(postId);
        model.addAttribute("post", findPost);
        return "posts/updateForm";
    }
}
