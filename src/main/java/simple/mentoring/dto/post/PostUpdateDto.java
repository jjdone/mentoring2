package simple.mentoring.dto.post;

import lombok.Data;
import simple.mentoring.domain.Post;

@Data
public class PostUpdateDto {

    private Long id;
    private String title;
    private String contents;

    public PostUpdateDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
    }
}
