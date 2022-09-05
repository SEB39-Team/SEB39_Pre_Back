package com.codestates.pre047.member.controller;

import com.codestates.pre047.member.dto.MemberDto;
import com.codestates.pre047.member.entity.Member;
import com.codestates.pre047.member.mapper.MemberMapper;
import com.codestates.pre047.member.service.MemberService;
import com.codestates.pre047.response.MultiResponseDto;
import com.codestates.pre047.response.SingleResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/v1/members")
public class MemberController {
    private MemberService memberService;
    private MemberMapper mapper;

    // 회원가입 처리
    @PostMapping("/create")
    public ResponseEntity registration(@Validated @RequestBody MemberDto.Post postMember) {

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

    // 메인 페이지
    @GetMapping("/")
    public String index() {

        return "/index";
    }

    // 회원가입 페이지
    @GetMapping("/signup")
    public String Signup() {
        return "/signup";
    }


    // 로그인 페이지
    @GetMapping("/login")
    public String Login() {
        return "/login";
    }

    // 로그인 결과 페이지
    @GetMapping("/user/login/result")
    public String LoginResult() {
        return "/loginSuccess";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/user/logout/result")
    public String Logout() {
        return "/logout";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String Denied() {
        return "/denied";
    }

    // 내 정보 페이지
    @GetMapping("/user/info")
    public String MyInfo() {
        return "/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String Admin() {
        return "/admin";
    }
}
