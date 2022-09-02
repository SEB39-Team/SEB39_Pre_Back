package com.codestates.pre047.answer.controller;


import com.codestates.pre047.answer.dto.AnswerDto;
import com.codestates.pre047.answer.entity.Answer;
import com.codestates.pre047.answer.mapper.AnswerMapper;
import com.codestates.pre047.answer.service.AnswerService;
import com.codestates.pre047.post.entity.Post;
import com.codestates.pre047.post.service.PostService;
import com.codestates.pre047.response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
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

    private final PostService postService;
    private final AnswerService answerService;
    private final AnswerMapper mapper;

    private static final String TEMPLATE_DIR = "answer";
    private static final String LIST_TEMPLATE = TEMPLATE_DIR+ "/list";


    @GetMapping
    public String findAnswers(Model model) {
        List<Answer> answers = answerService.findAll();

        model.addAttribute("answers", answers);

        return LIST_TEMPLATE;
    }

    @PostMapping("/{postId}")
    public ResponseEntity createAnswer(@PathVariable("postId") long postId,
                                       @RequestBody AnswerDto.Post answerPost) {

        Answer answer = mapper.AnswerPostToAnswer(answerPost);

        Post post = postService.findPost(postId);

        answer.setPost(post);
        answerService.save(answer);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.AnswerToAnswerDtoResponse(answer)), HttpStatus.CREATED);

    }

/*    @PatchMapping("/{answerId}")
    public ResponseEntity updateAnswer(@PathVariable("answerId") long answerId) {
        return null;
    }

    @DeleteMapping("/{answerId")
    public ResponseEntity deleteAnswer(@PathVariable("answerId") long answerId) {
        return null;
    }*/

}
