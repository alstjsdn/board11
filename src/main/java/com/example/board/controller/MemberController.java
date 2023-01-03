package com.example.board.controller;

import com.example.board.dto.MemberDto;
import com.example.board.entity.Member;
import com.example.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService  memberService;
    private final PasswordEncoder passwordEncoder;
    private User user;

    @GetMapping(value = "/new")
    public String newMember(Model model){
        model.addAttribute("memberDto",new MemberDto());

        return "memberForm.html";
    }

    @PostMapping(value = "/new")
    public String newMember(@Valid MemberDto memberDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "memberForm";
        }


        try {
            Member member = Member.createMember(memberDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "memberForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginMember() {

        return "memberLoginForm.html";
    }
    @GetMapping(value = "/login/error")
    public String loginMember(Model model) {
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해 주세요");
        return "memberLoginForm.html";
    }
    @GetMapping("/username")
    @ResponseBody
    public String currentUserName(Principal principal,Model model) {
        String userName = principal.getName();
        model.addAttribute("userName",userName);

        return userName;
        }
    }



