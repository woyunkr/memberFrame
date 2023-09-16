package com.pmember.service;

import com.pmember.dto.MemberFormDto;
import com.pmember.entity.Member;
import com.pmember.exception.EmailAlreadyExist;
import com.pmember.exception.ShowIdAlreadyExist;
import com.pmember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMemberEmail = memberRepository.findByEmail(member.getEmail());
        if (findMemberEmail != null) {
            throw new EmailAlreadyExist("이미 가입된 이메일입니다.");
        }

        Member findMemberId = memberRepository.findByShowId(member.getShowId());
        if (findMemberId != null) {
            throw new ShowIdAlreadyExist("이미 존재하는 아이디입니다.");
        }
    }

    @Override
    //이거 하니까 기본 로그인 정보 생성 안됨
    public UserDetails loadUserByUsername(String showId) throws UsernameNotFoundException {
        Member member = memberRepository.findByShowId(showId);

        if (member == null) {
            throw new UsernameNotFoundException(showId);
        }

        return User.builder()
                .username(showId)
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
