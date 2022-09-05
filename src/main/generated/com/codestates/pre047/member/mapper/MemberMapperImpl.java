package com.codestates.pre047.member.mapper;

import com.codestates.pre047.member.dto.MemberDto;
import com.codestates.pre047.member.entity.Member;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-05T23:47:39+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15.1 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostDtoToMember(MemberDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.username( requestBody.getUsername() );
        member.password( requestBody.getPassword() );
        member.email( requestBody.getEmail() );

        return member.build();
    }

    @Override
    public MemberDto.Response memberToMemberResponseDto(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDto.Response.ResponseBuilder response = MemberDto.Response.builder();

        response.username( member.getUsername() );

        return response.build();
    }

    @Override
    public List<MemberDto.Response> membersToMemberResponse(List<Member> members) {
        if ( members == null ) {
            return null;
        }

        List<MemberDto.Response> list = new ArrayList<MemberDto.Response>( members.size() );
        for ( Member member : members ) {
            list.add( memberToMemberResponseDto( member ) );
        }

        return list;
    }
}
