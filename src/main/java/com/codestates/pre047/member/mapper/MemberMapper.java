package com.codestates.pre047.member.mapper;


import com.codestates.pre047.member.dto.MemberDto;
import com.codestates.pre047.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

    Member memberPostDtoToMember(MemberDto.Post requestBody);

    MemberDto.Response memberToMemberResponseDto(Member member);

    List<MemberDto.Response> membersToMemberResponse(List<Member> members);
}
