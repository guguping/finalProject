package com.example.finalproject.controller;

import com.example.finalproject.dto.MemberDTO;
import com.example.finalproject.serivce.MailSendService;
import com.example.finalproject.serivce.MemberSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberSerivce memberSerivce;
    private final MailSendService mailSendService;

    @GetMapping("/save")
    public String saveForm() {
        return "memberPages/memberSave";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberDTO.setMemberBirth(memberDTO.getMemberBirthM() + memberDTO.getMemberBirthD() + memberDTO.getMemberBirthY());
        memberSerivce.save(memberDTO);
        return "redirect:/";
    }

    @GetMapping("/mailAuth")
    @ResponseBody
    public String mailAuth(String email) {
        System.out.println("이메일 인증 요청이 들어옴!");
        System.out.println("이메일 인증 이메일 : " + email);
        return mailSendService.createMailForm(email);
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model) {
        MemberDTO loginMemberDTO = memberSerivce.findByMemberEmailAndMemberPassword(memberDTO);
        if (loginMemberDTO != null) {
            session.setAttribute("memberId",loginMemberDTO.getId());
            System.out.println("LTQKF");
            return "redirect:/board/main";
        } else {
            String loginFalse = "아이디 비밀번호를 확인해주세요";
            model.addAttribute("loginFalse",loginFalse);
            System.out.println("로그인 실패");
            return "index";
        }
    }

}
