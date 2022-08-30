package com.codestates.pre047.post.mapper;

import com.codestates.pre047.post.dto.PostPatchDto;
import com.codestates.pre047.post.dto.PostPostDto;
import com.codestates.pre047.post.dto.PostResponseDto;
import com.codestates.pre047.post.entity.Post;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-30T12:57:49+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15.1 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post postPostDtoToPost(PostPostDto postPostDto) {
        if ( postPostDto == null ) {
            return null;
        }

        Post post = new Post();

        post.setTitle( postPostDto.getTitle() );
        post.setContent( postPostDto.getContent() );

        return post;
    }

    @Override
    public Post postPatchDtoToPost(PostPatchDto postPatchDto) {
        if ( postPatchDto == null ) {
            return null;
        }

        Post post = new Post();

        if ( postPatchDto.getPostId() != null ) {
            post.setPostId( postPatchDto.getPostId() );
        }
        post.setTitle( postPatchDto.getTitle() );
        post.setContent( postPatchDto.getContent() );

        return post;
    }

    @Override
    public PostResponseDto postToPostResponseDto(Post post) {
        if ( post == null ) {
            return null;
        }

        long postId = 0L;
        String title = null;
        String content = null;

        postId = post.getPostId();
        title = post.getTitle();
        content = post.getContent();

        PostResponseDto postResponseDto = new PostResponseDto( postId, title, content );

        return postResponseDto;
    }

    @Override
    public List<PostResponseDto> postsToPostsDtoResponseDtos(List<Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<PostResponseDto> list = new ArrayList<PostResponseDto>( posts.size() );
        for ( Post post : posts ) {
            list.add( postToPostResponseDto( post ) );
        }

        return list;
    }
}
