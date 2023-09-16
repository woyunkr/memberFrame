package com.pmember.controller;

import com.pmember.dto.MemberFormDto;
import com.pmember.entity.Member;
import com.pmember.service.MemberService;
import com.pmember.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "myPage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping(value = "main")
    public String myPageForm(){
        return "myPage/myPageForm";
    }

    @GetMapping(value = "myInfo")
    public String myInfo(Model model, Authentication authentication){

        String showId = authentication.getName();


        // 이메일을 기반으로 회원 정보를 가져와서 MemberFormDto에 설정합니다.
        MemberFormDto memberFormDto = myPageService.getMemberInfo(showId);


        model.addAttribute("memberInfo", memberFormDto);
        return "myPage/myInfo";
    }

    @GetMapping(value = "myProfile")
    public String myProfile(){
        return "myPage/myProfile";
    }
}
