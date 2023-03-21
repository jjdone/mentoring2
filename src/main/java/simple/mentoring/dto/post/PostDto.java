package simple.mentoring.dto.post;

import lombok.Data;
import lombok.NoArgsConstructor;
import simple.mentoring.domain.Post;
import simple.mentoring.domain.User;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class PostDto {

    private Long id;
    private Long userId;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "멘토링 상세 내용을 입력해주세요.")
    private String contents;
    private String writer;

    //Entity -> DTO
    public PostDto(Post post) {
        this.id = post.getId();
        this.userId = post.getUser().getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.writer = post.getWriter();
    }

    public Post toEntity(User user) {
        return Post.builder()
                .user(user)
                .writer(user.getNickname())
                .title(title)
                .contents(contents)
                .build();
    }
}
