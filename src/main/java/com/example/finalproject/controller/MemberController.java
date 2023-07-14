package com.example.finalproject.controller;

import com.example.finalproject.dto.BoardDTO;
import com.example.finalproject.dto.BoardFileDTO;
import com.example.finalproject.dto.MemberDTO;
import com.example.finalproject.dto.MemberFollowDTO;
import com.example.finalproject.serivce.BoardService;
import com.example.finalproject.serivce.MailSendService;
import com.example.finalproject.serivce.MemberFollowService;
import com.example.finalproject.serivce.MemberSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberSerivce memberSerivce;
    private final MailSendService mailSendService;
    private final BoardService boardService;
    private final MemberFollowService memberFollowService;

    @GetMapping("/save")
    public String saveForm() {
        return "memberPages/memberSave";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberDTO.setMemberBirth(memberDTO.getMemberBirthY() + memberDTO.getMemberBirthM() + memberDTO.getMemberBirthD());
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

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/myPage/{id}")
    public String memberMyPages(@PathVariable Long id, Model model) {
        // 해당 피드의 멤버 정보 가져오기
        MemberDTO memberDTO = memberSerivce.findById(id);
        // 해당 피드의 멤버가 작성한 board 정보 가져오기
        List<BoardDTO> boardDTOList = boardService.findByMemberId(id);
        List<BoardFileDTO> boardFileDTOList = boardService.findAllFileOrderByDesc();
        List<MemberFollowDTO> memberFollowerDTOList = memberFollowService.findByFollower(id);
        List<MemberFollowDTO> memberFollowingDTOList = memberFollowService.findByFollowing(id);
        int memberFollowerCount = memberFollowerDTOList.size();
        int memberFollowingCount = memberFollowingDTOList.size();

        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("boardDTOList", boardDTOList);
        model.addAttribute("boardFileDTOList", boardFileDTOList);
        model.addAttribute("memberFollowerCount", memberFollowerCount);
        model.addAttribute("memberFollowingCount", memberFollowingCount);
        return "memberPages/myPage";
    }

    @GetMapping("/findPwAuth")
    public String findPwAuthForm() {
        return "memberPages/memberReset";
    }

    @GetMapping("/findPw")
    public String findPwForm() {
        return "memberPages/passwordUpdate";
    }

    @PostMapping("/profileUpdate")
    public String profileUpdate(@ModelAttribute MemberDTO memberDTO, HttpSession session) throws IOException {
        Long loginId = (Long) session.getAttribute("memberId");
        memberDTO.setId(loginId);
        memberSerivce.profileUpdate(memberDTO);
        return "redirect:/member/myPage/" + loginId;
    }

    @GetMapping("/profileDelete")
    public String profileDelete(HttpSession session) {
        Long loginId = (Long) session.getAttribute("memberId");
        memberSerivce.profileDelete(loginId);
        return "redirect:/member/myPage/" + loginId;
    }

}
