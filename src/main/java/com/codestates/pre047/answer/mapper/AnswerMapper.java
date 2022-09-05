package com.codestates.pre047.answer.mapper;


import com.codestates.pre047.answer.dto.AnswerDto;
import com.codestates.pre047.answer.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "Spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {

    Answer AnswerPostToAnswer(AnswerDto.Post answerDto);

    Answer AnswerPatchToAnswer(AnswerDto.Patch answerDto);

    AnswerDto.Response AnswerToAnswerDtoResponse(Answer answer);

}
