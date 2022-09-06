/*
package com.codestates.pre047.post;


import com.codestates.pre047.helper.PostControllerTestHelper;
import com.codestates.pre047.helper.StubData;
import com.codestates.pre047.helper.StubData.MockPost;
import com.codestates.pre047.post.controller.PostController;
import com.codestates.pre047.post.dto.PostDto;
import com.codestates.pre047.post.entity.Post;
import com.codestates.pre047.post.mapper.PostMapper;
import com.codestates.pre047.post.service.PostService;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static com.codestates.pre047.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.codestates.pre047.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class PostControllerDocumentation implements PostControllerTestHelper {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @MockBean
    private PostMapper mapper;

    @Test
    public void postPostTest() throws Exception {

        //given
        PostDto.Post post = (PostDto.Post) MockPost.getRequestBody(HttpMethod.POST);
        String content = toJsonContent(post);

        PostDto.Response responseBody = StubData.MockPost.getSingleResponseBody();

        given(mapper.postPostToPost(Mockito.any(PostDto.Post.class))).willReturn(new Post());

        given(postService.createPost(Mockito.any(Post.class))).willReturn(new Post());

        given(mapper.postToPostResponse(Mockito.any(Post.class))).willReturn(responseBody);

        //when

        ResultActions actions =
                mockMvc.perform(postRequestBuilder(getUrl(), content));

        //then

        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.title").value(post.getTitle()))
                .andExpect(jsonPath("$.data.content").value(post.getContent()))
                .andDo(document("post-post",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                getDefaultPostPostRequestDescriptors()
                        ),
                        responseFields(
                                getFullResponseDescriptors(
                                        getDefaultPostResponseDescriptors(DataResponseType.SINGLE))
                        )
                ));
    }

    @Test
    public void patchPostTest() throws Exception {

        //given
        long postId = 1L;
        PostDto.Patch patch = (PostDto.Patch) StubData.MockPost.getRequestBody(HttpMethod.PATCH);
        String content = toJsonContent(patch);

        PostDto.Response responseDto = StubData.MockPost.getSingleResponseBody();

        given(mapper.postPatchToPost(Mockito.any(PostDto.Patch.class))).willReturn(new Post());

        given(postService.updatePost(Mockito.any(Post.class))).willReturn(new Post());

        given(mapper.postToPostResponse(Mockito.any(Post.class))).willReturn(responseDto);

        //when

        ResultActions actions = mockMvc.perform(patchRequestBuilder(getURI(), postId, content));

        //then

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.postId").value(patch.getPostId()))
                .andExpect(jsonPath("$.data.title").value(patch.getTitle()))
                .andExpect(jsonPath("$.data.content").value(patch.getContent()))
                .andDo(document("patch-post",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                getPostRequestPathParameterDescriptor()
                        ),
                        requestFields(
                                getDefaultPostPatchRequestDescriptors()
                        ),
                        responseFields(
                                getFullResponseDescriptors(
                                        getDefaultPostResponseDescriptors(DataResponseType.SINGLE))
                        )
                ));

    }


    @Test
    public void getPostTest() throws Exception {
        // given
        long postId = 1L;
        PostDto.Response response = StubData.MockPost.getSingleResponseBody();

        given(postService.findPost(Mockito.anyLong())).willReturn(new Post());
        given(mapper.postToPostResponse(Mockito.any(Post.class))).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform(getRequestBuilder(getURI(), postId));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.postId").value(postId))
                .andExpect(jsonPath("$.data.title").value(response.getTitle()))
                .andExpect(jsonPath("$.data.content").value(response.getContent()))

                .andDo(
                        document("get-post",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        getPostRequestPathParameterDescriptor()
                                ),
                                responseFields(
                                        getFullResponseDescriptors(
                                                getDefaultPostResponseDescriptors(DataResponseType.SINGLE))
                                )
                        ));
    }

    @Test
    public void getPostsTest() throws Exception {
        // given
        String page = "1";
        String size = "10";

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);

        Page<Post> posts = StubData.MockPost.getMultiResultPost();
        List<PostDto.Response> responses = StubData.MockPost.getMultiResponseBody();

        // Stubbing
        given(postService.findPosts(Mockito.anyInt(), Mockito.anyInt())).willReturn(posts);
        given(mapper.postsToPostResponses(Mockito.anyList())).willReturn(responses);

        // when
        ResultActions actions = mockMvc.perform(getRequestBuilder(getUrl(), queryParams));

        // then
        MvcResult result =
                actions
                        .andExpect(status().isOk())
                        .andDo(
                                document(
                                        "get-posts",
                                        getRequestPreProcessor(),
                                        getResponsePreProcessor(),
                                        requestParameters(
                                                getDefaultRequestParameterDescriptors()
                                        ),
                                        responseFields(
                                                getFullPageResponseDescriptors(
                                                        getDefaultPostResponseDescriptors(DataResponseType.LIST))

                                        )
                                )
                        )
                        .andReturn();

        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");
        assertThat(list.size(), is(2));
    }

    @Test
    public void deletePostTest() throws Exception {
        // given
        long postId = 1L;
        doNothing().when(postService).deletePost(Mockito.anyLong());

        // when
        mockMvc.perform(deleteRequestBuilder(getURI(), postId))
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-post",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        getPostRequestPathParameterDescriptor()
                                )
                        )
                );
        // then
    }

}
*/
