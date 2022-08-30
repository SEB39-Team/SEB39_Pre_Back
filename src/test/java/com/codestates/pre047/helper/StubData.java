package com.codestates.pre047.helper;

import com.codestates.pre047.post.dto.PostDto;
import com.codestates.pre047.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StubData {
    private static Map<HttpMethod, Object> stubRequestBody;

    static {
        stubRequestBody = new HashMap<>();
        stubRequestBody.put(HttpMethod.POST, new PostDto.Post("title", "content"));
        stubRequestBody.put(HttpMethod.PATCH, new PostDto.Patch(1, "title", "content"));
    }

    public static class MockPost {
        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }
        public static PostDto.Response getSingleResponseBody() {
            return new PostDto.Response(1L, "title", "content");
        }

        public static List<PostDto.Response> getMultiResponseBody() {
            return List.of(
                    new PostDto.Response(1L, "title1", "content1"),
                    new PostDto.Response(2L, "title2", "content2")
            );
        }

        public static Post getSingleResultPost() {
            Post post = new Post("title", "content");
            return post;
        }
        public static Page<Post> getMultiResultPost() {
            Post post1 = new Post("title1", "content1");

            Post post2 = new Post("title2", "content2");

            return new PageImpl<>(List.of(post1, post2),
                    PageRequest.of(0, 10, Sort.by("memberId").descending()),
                    2);
        }

        public static Post getSingleResultPost(long postId) {
            Post post = new Post("title", "content");
            post.setPostId(postId);
            return post;
        }

        public static Post getSingleResultPost(long postId, Map<String, String> updatedInfo) {
            String title = Optional.ofNullable(updatedInfo.get("title")).orElse("title");
            String content = Optional.ofNullable(updatedInfo.get("content")).orElse("content");
            Post post = new Post(title, content);
            post.setPostId(postId);
            return post;
        }
    }



}
