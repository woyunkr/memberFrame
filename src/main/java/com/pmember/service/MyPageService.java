package com.pmember.service;

import com.pmember.dto.MemberFormDto;
import com.pmember.entity.Member;
import com.pmember.entity.MyPage;
import com.pmember.repository.MemberRepository;
import com.pmember.repository.MyPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageService {

    private final MemberRepository memberRepository;
    private final MyPageRepository myPageRepository;

    public MemberFormDto getMemberInfo(String showId) {
        Member member = memberRepository.findByShowId(showId);
        MemberFormDto memberInfo = new MemberFormDto();
        memberInfo.setEmail(member.getEmail());
        memberInfo.setName(member.getName());
        memberInfo.setPassword(member.getPassword());

        return memberInfo;
    }

}
