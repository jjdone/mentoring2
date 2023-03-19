package simple.mentoring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simple.mentoring.domain.Post;
import simple.mentoring.domain.User;
import simple.mentoring.dto.post.PostDto;
import simple.mentoring.dto.post.PostUpdateDto;
import simple.mentoring.repository.PostRepository;
import simple.mentoring.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long upload(PostDto postDto) {
        User user = userRepository.findByNickname(postDto.getWriter()).get();
        Post post = postDto.toEntity(user);
        return postRepository.save(post).getId();
    }

//    @Transactional(readOnly = true)
//    public List<PostDto> findPostDtoList() {
//        List<Post> posts = postRepository.findAll();
//        return posts.stream()
//                .map(PostDto::new)
//                .collect(Collectors.toList());
//    }

    @Transactional(readOnly = true)
    public PostDto findById(Long postId) {
        return new PostDto(postRepository.findById(postId).get());
    }

    @Transactional(readOnly = true)
    public PostUpdateDto getPostUpdateDto(Long postId) {
        return new PostUpdateDto(postRepository.findById(postId).get());
    }

    @Transactional
    public Long update(Long postId, PostUpdateDto postUpdateDto) {
        Post findPost = postRepository.findById(postId).get();
        findPost.update(postUpdateDto);
        return findPost.getId();
    }

    @Transactional(readOnly = true)
    public Page<Post> pageList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
