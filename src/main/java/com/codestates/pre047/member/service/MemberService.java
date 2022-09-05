package com.codestates.pre047.member.service;


import com.codestates.pre047.domain.Role;
import com.codestates.pre047.exception.BusinessLogicException;
import com.codestates.pre047.exception.ExceptionCode;
import com.codestates.pre047.member.entity.Member;
import com.codestates.pre047.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;*/
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class MemberService /*implements UserDetailsService*/ {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        verifiedMemberEmail(member.getEmail());
/*        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));*/
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
        try {
            memberRepository.deleteById(memberId);
        } catch (EmptyResultDataAccessException ex) {
            log.info("Delete non existing entity with id=" + memberId, ex);
        }
    }


/*    @Transactional
    public Long joinUser(MemberDto memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity()).getId();
    }*/

    public void verifiedMemberEmail(String email) {
        Optional<Member> verifyMember = memberRepository.findByEmail(email);
        if (verifyMember.isPresent()) throw new BusinessLogicException(ExceptionCode.MEMBER_EXIST);
    }



/*    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<Member> userEntityWrapper = memberRepository.findByEmail(userEmail);
        Member userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(userEmail)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }*/
}
