package simple.mentoring.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simple.mentoring.dto.post.PostDto;
import simple.mentoring.dto.post.PostUpdateDto;
import simple.mentoring.service.PostService;

@RequestMapping("/api/posts")
@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@ModelAttribute("form") PostDto postDto) {
        return ResponseEntity.ok(postService.upload(postDto));
    }

    @PostMapping("/{postId}/update")
    public ResponseEntity<?> update(@PathVariable Long postId,
                                    @ModelAttribute("post") PostUpdateDto postUpdateDto) {
        return ResponseEntity.ok(postService.update(postId, postUpdateDto));
    }
}
