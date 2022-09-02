package com.codestates.pre047.answer.service;

import com.codestates.pre047.answer.entity.Answer;
import com.codestates.pre047.answer.repository.AnswerRepository;
import com.codestates.pre047.exception.BusinessLogicException;
import com.codestates.pre047.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService{

    private final AnswerRepository answerRepository;

    @Override
    public Answer save(Answer answer) {


        return answerRepository.save(answer);
    }

    @Override
    public Answer update(Answer answer) {

        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());
        return answerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(long answerId) {
        try {
            answerRepository.deleteById(answerId);
        } catch (EmptyResultDataAccessException ex) {
            log.info("Delete non existing entity with id=" + answerId, ex);
        }
    }

    @Override
    public Optional<Answer> findAnswers(long answerId) {

        return answerRepository.findById(answerId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }


    private Answer findVerifiedAnswer(long replyId) {
        Optional<Answer> answer = answerRepository.findById(replyId);
        Answer findAnswer = answer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.POST_NOT_FOUND));
        // 에외처리 수정 요망

        System.out.println(findAnswer.getContent());

        return findAnswer;
    }
}
