package com.codestates.pre047.answer.mapper;

import com.codestates.pre047.answer.dto.AnswerDto;
import com.codestates.pre047.answer.entity.Answer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-05T14:47:23+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15.1 (Oracle Corporation)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public Answer AnswerPostToAnswer(AnswerDto.Post answerDto) {
        if ( answerDto == null ) {
            return null;
        }

        Answer.AnswerBuilder answer = Answer.builder();

        answer.content( answerDto.getContent() );

        return answer.build();
    }

    @Override
    public Answer AnswerPatchToAnswer(AnswerDto.Patch answerDto) {
        if ( answerDto == null ) {
            return null;
        }

        Answer.AnswerBuilder answer = Answer.builder();

        answer.content( answerDto.getContent() );

        return answer.build();
    }

    @Override
    public AnswerDto.Response AnswerToAnswerDtoResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerDto.Response response = new AnswerDto.Response();

        response.setContent( answer.getContent() );

        return response;
    }
}
