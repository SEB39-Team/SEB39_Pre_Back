package com.codestates.pre047.post.mapper;

import com.codestates.pre047.post.dto.PostDto;
import com.codestates.pre047.post.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    Post postPostToPost(PostDto.Post requestBody);
    Post postPatchToPost(PostDto.Patch requestBody);
    PostDto.Response postToPostResponse(Post post);
    List<PostDto.Response> postsToPostResponses(List<Post> posts);
}
