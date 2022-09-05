package com.codestates.pre047.post.mapper;

import com.codestates.pre047.answer.dto.AnswerDto;
import com.codestates.pre047.answer.entity.Answer;
import com.codestates.pre047.post.dto.PostDto;
import com.codestates.pre047.post.entity.Post;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-05T14:47:23+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15.1 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post postPostToPost(PostDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Post post = new Post();

        post.setTitle( requestBody.getTitle() );
        post.setContent( requestBody.getContent() );

        return post;
    }

    @Override
    public Post postPatchToPost(PostDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Post post = new Post();

        post.setPostId( requestBody.getPostId() );
        post.setTitle( requestBody.getTitle() );
        post.setContent( requestBody.getContent() );

        return post;
    }

    @Override
    public PostDto.Response postToPostResponse(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDto.Response.ResponseBuilder response = PostDto.Response.builder();

        response.postId( post.getPostId() );
        response.title( post.getTitle() );
        response.content( post.getContent() );

        return response.build();
    }

    @Override
    public PostDto.AnswerResponse postToAnswerResponse(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDto.AnswerResponse.AnswerResponseBuilder answerResponse = PostDto.AnswerResponse.builder();

        answerResponse.postId( post.getPostId() );
        answerResponse.title( post.getTitle() );
        answerResponse.content( post.getContent() );
        answerResponse.answers( answerListToResponseList( post.getAnswers() ) );

        return answerResponse.build();
    }

    @Override
    public List<PostDto.Response> postsToPostResponses(List<Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<PostDto.Response> list = new ArrayList<PostDto.Response>( posts.size() );
        for ( Post post : posts ) {
            list.add( postToPostResponse( post ) );
        }

        return list;
    }

    protected AnswerDto.Response answerToResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerDto.Response response = new AnswerDto.Response();

        response.setContent( answer.getContent() );

        return response;
    }

    protected List<AnswerDto.Response> answerListToResponseList(List<Answer> list) {
        if ( list == null ) {
            return null;
        }

        List<AnswerDto.Response> list1 = new ArrayList<AnswerDto.Response>( list.size() );
        for ( Answer answer : list ) {
            list1.add( answerToResponse( answer ) );
        }

        return list1;
    }
}
