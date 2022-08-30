package com.codestates.pre047.post.mapper;

import com.codestates.pre047.post.dto.PostDto.Patch;
import com.codestates.pre047.post.dto.PostDto.Post;
import com.codestates.pre047.post.dto.PostDto.Response;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-30T17:22:51+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15.1 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public com.codestates.pre047.post.entity.Post postPostToPost(Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        com.codestates.pre047.post.entity.Post post = new com.codestates.pre047.post.entity.Post();

        post.setTitle( requestBody.getTitle() );
        post.setContent( requestBody.getContent() );

        return post;
    }

    @Override
    public com.codestates.pre047.post.entity.Post postPatchToPost(Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        com.codestates.pre047.post.entity.Post post = new com.codestates.pre047.post.entity.Post();

        post.setPostId( requestBody.getPostId() );
        post.setTitle( requestBody.getTitle() );
        post.setContent( requestBody.getContent() );

        return post;
    }

    @Override
    public Response postToPostResponse(com.codestates.pre047.post.entity.Post post) {
        if ( post == null ) {
            return null;
        }

        long postId = 0L;
        String title = null;
        String content = null;

        postId = post.getPostId();
        title = post.getTitle();
        content = post.getContent();

        Response response = new Response( postId, title, content );

        return response;
    }

    @Override
    public List<Response> postsToPostsResponseDtos(List<com.codestates.pre047.post.entity.Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<Response> list = new ArrayList<Response>( posts.size() );
        for ( com.codestates.pre047.post.entity.Post post : posts ) {
            list.add( postToPostResponse( post ) );
        }

        return list;
    }
}
