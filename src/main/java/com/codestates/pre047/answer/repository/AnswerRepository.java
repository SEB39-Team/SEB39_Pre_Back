package com.codestates.pre047.answer.repository;

import com.codestates.pre047.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
