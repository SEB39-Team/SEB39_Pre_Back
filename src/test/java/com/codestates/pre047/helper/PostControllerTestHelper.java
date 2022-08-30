package com.codestates.pre047.helper;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public interface PostControllerTestHelper extends ControllerTestHelper{
    String POST_URL = "/v1/posts";
    String RESOURCE_URI = "/{post-id}";

    default String getUrl() {
        return POST_URL;
    }

    default String getURI() {
        return POST_URL + RESOURCE_URI;
    }

    default List<ParameterDescriptor> getPostRequestPathParameterDescriptor() {
        return Arrays.asList(parameterWithName("post-id").description("게시글 식별자 ID"));
    }

    default List<FieldDescriptor> getDefaultPostPostRequestDescriptors() {

        return List.of(
                fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                fieldWithPath("content").type(JsonFieldType.STRING).description("내용")
        );
    }

    default List<FieldDescriptor> getDefaultPostPatchRequestDescriptors() {

        return List.of(
                fieldWithPath("postId").type(JsonFieldType.NUMBER).description("게시글 식별자").ignored(),
                fieldWithPath("title").type(JsonFieldType.STRING).description("제목").optional(),
                fieldWithPath("content").type(JsonFieldType.STRING).description("내용").optional()
        );
    }

    default List<FieldDescriptor> getDefaultPostResponseDescriptors(DataResponseType dataResponseType) {
        String parentPath = getDataParentPath(dataResponseType);
        return List.of(
                fieldWithPath(parentPath.concat("postId")).type(JsonFieldType.NUMBER).description("게시글 식별자"),
                fieldWithPath(parentPath.concat("title")).type(JsonFieldType.STRING).description("제목"),
                fieldWithPath(parentPath.concat("content")).type(JsonFieldType.STRING).description("내용")
        );
    }

}
