package com.codestates.pre047.post.mapper;

import com.codestates.pre047.post.dto.PostPostDto;
import com.codestates.pre047.post.dto.PostResponseDto;
import com.codestates.pre047.post.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public Post postPostDtoToPost(PostPostDto postPostDto) {
        return new Post(0L,
                postPostDto.getTitle(),
                postPostDto.getContent());
    }

    public PostResponseDto postToPostResponseDto(Post post) {
        return new PostResponseDto(post.getPostId(),
                post.getTitle(), post.getContent());
    }
}
