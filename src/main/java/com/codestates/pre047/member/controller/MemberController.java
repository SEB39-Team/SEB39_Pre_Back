package com.codestates.pre047.member.controller;


import com.codestates.pre047.dto.MultiResponseDto;
import com.codestates.pre047.dto.SingleResponseDto;
import com.codestates.pre047.member.dto.MemberDto;
import com.codestates.pre047.member.entity.Member;
import com.codestates.pre047.member.mapper.MemberMapper;
import com.codestates.pre047.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/v1/members")
@Validated
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;


    @PostMapping("/join")
    public ResponseEntity join(@Validated @RequestBody MemberDto.Post postMember) {

        Member member = mapper.memberPostDtoToMember(postMember);
        memberService.saveMember(member);

        return new ResponseEntity<>("회원 가입 완료", HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity findMember(@PathVariable("member-id") Long memberId) {
        Member findMember = memberService.findById(memberId);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponseDto(findMember)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getmembers(@RequestParam @Positive int page, @RequestParam @Positive int size) {
        Page<Member> pagemembers = memberService.findAllMembers(page - 1, size);

        List<Member> members = pagemembers.getContent();

        List<MemberDto.Response> responses = mapper.membersToMemberResponse(members);

        return new ResponseEntity<>(new MultiResponseDto<>(responses, pagemembers), HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public void deleteMember(@PathVariable("member-id") Long memberId) {

        memberService.deleteMember(memberId);
    }

    @GetMapping("/user")
    public ResponseEntity user() {
        return new ResponseEntity<>("user 권한을 가지고 있습니다.", HttpStatus.OK);
    }


    @GetMapping("/manager")
    public ResponseEntity manager() {
        return new ResponseEntity<>("manager 권한을 가지고 있습니다.", HttpStatus.OK);
    }


    @GetMapping("/admin")
    public ResponseEntity admin() {
        return new ResponseEntity<>("admin 권한을 가지고 있습니다. ", HttpStatus.OK);
    }


    @GetMapping("/login")
    public ResponseEntity login() {

        return new ResponseEntity<>("토큰이 존재하지 않습니다. ", HttpStatus.OK);
    }
}
