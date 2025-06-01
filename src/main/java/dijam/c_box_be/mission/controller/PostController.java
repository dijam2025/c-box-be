package dijam.c_box_be.mission.controller;

import dijam.c_box_be.mission.dto.*;
import dijam.c_box_be.mission.service.PostService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto dto) {
        return ResponseEntity.ok(postService.createPost(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostDetail(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }
}

