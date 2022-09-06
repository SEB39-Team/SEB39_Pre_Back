package com.codestates.pre047.member.service;


import com.codestates.pre047.exception.BusinessLogicException;
import com.codestates.pre047.exception.ExceptionCode;
import com.codestates.pre047.member.entity.Member;
import com.codestates.pre047.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService  {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member saveMember(Member member) {

        verifiedMemberEmail(member.getEmail());
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        member.setRoles("ROLE_USER");

        return memberRepository.save(member);
    }
    public Member findById(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.INFO_NOT_FOUND));

        return member;
    }

    public Page<Member> findAllMembers(int page, int size) {

        return memberRepository.findAll(PageRequest.of(page, size, Sort.by("memberId").descending()));
    }
    public void deleteMember(Long memberId) {
        Member findMember = findVerifiedMember(memberId);
        memberRepository.delete(findMember);
    }

    public void verifiedMemberEmail(String email) {
        Optional<Member> verifyMember = memberRepository.findByEmail(email);
        if (verifyMember.isPresent()) throw new BusinessLogicException(ExceptionCode.MEMBER_EXIST);
    }

    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.INFO_NOT_FOUND));
        return findMember;
    }


}
