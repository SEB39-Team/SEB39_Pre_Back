package com.codestates.pre047.answer.service;

import com.codestates.pre047.answer.entity.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerService {

    Answer save(Answer answer);

    Answer update(Answer answer);

    void deleteAnswer(long answerId);

    Optional<Answer> findAnswers(long answerId);

    List<Answer> findAll();


}
