package simple.mentoring.dto.post;

import lombok.Data;
import simple.mentoring.domain.Post;

@Data
public class PostUpdateDto {

    private String title;
    private String contents;

    public PostUpdateDto(Post post) {
        this.title = post.getTitle();
        this.contents = post.getContents();
    }
}
