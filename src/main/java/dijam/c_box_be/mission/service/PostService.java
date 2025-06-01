package dijam.c_box_be.mission.service;

import dijam.c_box_be.mission.dto.PostRequestDto;
import dijam.c_box_be.mission.dto.PostResponseDto;
import dijam.c_box_be.mission.entity.Post;
import dijam.c_box_be.mission.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public PostResponseDto createPost(PostRequestDto dto) {
        Post post = Post.builder()
                .title(dto.getTitle())
                .category(dto.getCategory())
                .createdAt(LocalDateTime.now())
                .comments(0)
                .build();
        return toDto(postRepository.save(post));
    }

    public PostResponseDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return toDto(post);
    }

    private PostResponseDto toDto(Post post) {
        return new PostResponseDto(
                post.getUserId(),
                post.getCategory(),
                post.getTitle(),
                post.getComments(),
                post.getCreatedAt()
        );
    }
}
