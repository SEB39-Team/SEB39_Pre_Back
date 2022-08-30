package com.codestates.pre047.post.mapper;

import com.codestates.pre047.post.dto.PostPatchDto;
import com.codestates.pre047.post.dto.PostPostDto;
import com.codestates.pre047.post.dto.PostResponseDto;
import com.codestates.pre047.post.entity.Post;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post postPostDtoToPost(PostPostDto postPostDto);
    Post postPatchDtoToPost(PostPatchDto postPatchDto);
    PostResponseDto postToPostResponseDto(Post post);
    List<PostResponseDto> postsToPostsDtoResponseDtos(Page<Post> posts);
}
