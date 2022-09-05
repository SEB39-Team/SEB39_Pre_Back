package com.codestates.pre047.answer.controller;


import com.codestates.pre047.answer.dto.AnswerDto;
import com.codestates.pre047.answer.entity.Answer;
import com.codestates.pre047.answer.mapper.AnswerMapper;
import com.codestates.pre047.answer.service.AnswerService;
import com.codestates.pre047.post.entity.Post;
import com.codestates.pre047.post.service.PostService;
import com.codestates.pre047.response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RequiredArgsConstructor
@RequestMapping("/v1/answers")
@RestController
@Validated
public class AnswerController {

    private final AnswerService answerService;
    private final AnswerMapper mapper;

    @PostMapping("/{postId}")
    public ResponseEntity createAnswer(@PathVariable("postId") long postId,
                                       @RequestBody AnswerDto.Post answerPost) {

        Answer answer = mapper.AnswerPostToAnswer(answerPost);
        answerService.save(postId,answer);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.AnswerToAnswerDtoResponse(answer)), HttpStatus.CREATED);

    }

    @PatchMapping("/{answerId}")
    public ResponseEntity updateAnswer(@PathVariable("answerId") long answerId, @RequestBody AnswerDto.Patch answerPatch)
    {
        Answer answer = mapper.AnswerPatchToAnswer(answerPatch);
        answer.setAnswerId(answerId);
        Answer response = answerService.update(answer);


        return new ResponseEntity<>(new SingleResponseDto<>(mapper.AnswerToAnswerDtoResponse(response)),HttpStatus.OK);
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity deleteAnswer(@PathVariable("answerId") long answerId) {

        answerService.deleteAnswer(answerId);


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
