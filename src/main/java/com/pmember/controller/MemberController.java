package com.pmember.controller;

import com.pmember.dto.MemberFormDto;
import com.pmember.entity.Member;
import com.pmember.exception.EmailAlreadyExist;
import com.pmember.exception.ShowIdAlreadyExist;
import com.pmember.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping(value = "/new")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "member/memberForm";
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (EmailAlreadyExist e) {
            // 이미 가입된 이메일이 있는 경우 오류 메시지를 이메일 관련 변수에 저장합니다.
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        } catch (ShowIdAlreadyExist e) {
            // 이미 가입된 아이디가 있는 경우 오류 메시지를 아이디 관련 변수에 저장합니다.
            model.addAttribute("errorMessage1", e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";
    }


    @GetMapping(value = "/login")
    public String memberLoginForm(Model model) {
        return "member/memberLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "member/memberLoginForm";
    }

    @GetMapping(value = "/logout")
    public String logOut(Model model) {
        return "redirect:/";
    }
}
