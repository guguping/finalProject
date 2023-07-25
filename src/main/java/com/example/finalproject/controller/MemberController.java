package com.example.finalproject.controller;

import com.example.finalproject.dto.BoardDTO;
import com.example.finalproject.dto.BoardFileDTO;
import com.example.finalproject.dto.MemberDTO;
import com.example.finalproject.dto.MemberFollowDTO;
import com.example.finalproject.entitiy.MemberEntity;
import com.example.finalproject.serivce.BoardService;
import com.example.finalproject.serivce.MailSendService;
import com.example.finalproject.serivce.MemberFollowService;
import com.example.finalproject.serivce.MemberSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//    @RequestMapping("/logout")
//    public String logout(Authentication authentication, HttpServletRequest request, HttpSession session) {
//        String authorizationHeader = request.getHeader("Authorization");
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            String accessToken = authorizationHeader.substring("Bearer ".length());
//            // 액세스 토큰을 사용하여 로그아웃 처리
//            if (authentication != null && authentication.isAuthenticated()) {
//                // 일반 로그인일 경우
//                // 로그아웃 처리
//                authentication.setAuthenticated(false);
//                SecurityContextHolder.clearContext();
//            } else {
////                 카카오 로그인일 경우
////                 카카오 로그아웃 API 호출
//                String kakaoLogoutUrl = "https://kapi.kakao.com/v1/user/logout";
//                HttpHeaders headers = new HttpHeaders();
//                headers.add("Authorization", "Bearer " + accessToken);
//                HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
//                RestTemplate restTemplate = new RestTemplate();
//                restTemplate.exchange(kakaoLogoutUrl, HttpMethod.POST, requestEntity, String.class);
//            }
//
//            // 세션 초기화
//            session.invalidate();
//
//            return "redirect:/"; // 로그아웃 후 리다이렉트할 페이지
//        } else {
//            // Authorization 헤더가 없거나 올바르지 않은 경우 처리
//            // 오류 처리 또는 다른 로직 수행
//            return "error";
//        }
//    }

//    @RequestMapping("/logout")
//    public String kakaoLogout(HttpSession session) {
//        String accessToken = (String) session.getAttribute("accessToken");
//        System.out.println(accessToken);
//
//        // 액세스 토큰을 사용하여 카카오 로그아웃 API 호출
//        if (accessToken != null) {
//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders headers = new HttpHeaders();
//            headers.setBearerAuth(accessToken);
//            HttpEntity<String> entity = new HttpEntity<>(headers);
//
//            String kakaoLogoutUrl = "https://kapi.kakao.com/v1/user/logout";
//            ResponseEntity<String> response = restTemplate.exchange(kakaoLogoutUrl, HttpMethod.POST, entity, String.class);
//
//            if (response.getStatusCode().is2xxSuccessful()) {
//                // 카카오 로그아웃 성공
//                session.removeAttribute("accessToken");
//                session.removeAttribute("memberId");
//                session.invalidate();
//                return "redirect:/"; // 로그아웃 후 리다이렉트할 페이지
//            } else {
//                // 카카오 로그아웃 실패
//                return "error"; // 오류 페이지
//            }
//        } else {
//            // 액세스 토큰이 없는 경우
//            return "error"; // 오류 페이지
//        }
//    }


    @GetMapping("/myPage/{id}")
    public String memberMyPages(@PathVariable Long id, Model model, HttpSession session) {
        Long loginId = (Long) session.getAttribute("memberId");
        MemberDTO loginMemberDTO = memberSerivce.findById(loginId);
        // 해당 피드의 멤버 정보 가져오기
        MemberDTO memberDTO = memberSerivce.findById(id);
        // 해당 피드의 멤버가 작성한 board 정보 가져오기
        List<BoardDTO> boardDTOList = boardService.findByMemberId(id);
        List<BoardFileDTO> boardFileDTOList = boardService.findAllFileOrderByDesc();
        List<MemberFollowDTO> memberFollowerDTOList = memberFollowService.findByFollower(id);
        List<MemberFollowDTO> memberFollowingDTOList = memberFollowService.findByFollowing(id);
        int followOk = 0;
        for (MemberFollowDTO f : memberFollowerDTOList) {
            if (f.getFollowerId() == loginId) {
                followOk = 1;
                break;
            }
        }
        int memberFollowerCount = memberFollowerDTOList.size();
        int memberFollowingCount = memberFollowingDTOList.size();

        model.addAttribute("memberDTO", loginMemberDTO);
        model.addAttribute("pageMemberDTO", memberDTO);
        model.addAttribute("boardDTOList", boardDTOList);
        model.addAttribute("boardFileDTOList", boardFileDTOList);
        model.addAttribute("memberFollowerCount", memberFollowerCount);
        model.addAttribute("memberFollowingCount", memberFollowingCount);
        model.addAttribute("memberFollowOk", followOk);
        return "memberPages/myPage";
    }

    @GetMapping("/myPage1/{id}")
    public String memberMyPages1(@PathVariable Long id, Model model, HttpSession session) {
        Long loginId = (Long) session.getAttribute("memberId");
        MemberDTO loginMemberDTO = memberSerivce.findById(loginId);
        // 해당 피드의 멤버 정보 가져오기
        MemberDTO memberDTO = memberSerivce.findById(id);
        // 해당 피드의 멤버가 작성한 board 정보 가져오기
        memberSerivce.searchSaved(loginId, id);
        List<BoardDTO> boardDTOList = boardService.findByMemberId(id);
        List<BoardFileDTO> boardFileDTOList = boardService.findAllFileOrderByDesc();
        List<MemberFollowDTO> memberFollowerDTOList = memberFollowService.findByFollower(id);
        List<MemberFollowDTO> memberFollowingDTOList = memberFollowService.findByFollowing(id);
        int followOk = 0;
        for (MemberFollowDTO f : memberFollowerDTOList) {
            if (f.getFollowerId() == loginId) {
                followOk = 1;
                break;
            }
        }
        int memberFollowerCount = memberFollowerDTOList.size();
        int memberFollowingCount = memberFollowingDTOList.size();

        model.addAttribute("memberDTO", loginMemberDTO);
        model.addAttribute("pageMemberDTO", memberDTO);
        model.addAttribute("boardDTOList", boardDTOList);
        model.addAttribute("boardFileDTOList", boardFileDTOList);
        model.addAttribute("memberFollowerCount", memberFollowerCount);
        model.addAttribute("memberFollowingCount", memberFollowingCount);
        model.addAttribute("memberFollowOk", followOk);
        return "memberPages/myPage";
    }

    @PostMapping("/profileUpdate")
    public String profileUpdate(@ModelAttribute MemberDTO memberDTO, HttpSession session) throws IOException {
        Long loginId = (Long) session.getAttribute("memberId");
        memberDTO.setId(loginId);
        memberSerivce.profileUpdate(memberDTO);
        return "redirect:/member/myPage/" + loginId;
    }
    @PostMapping("/profileUpdate1")
    public ResponseEntity<?> profileUpdate1(@RequestParam("memberFile") MultipartFile memberFile, HttpSession session) throws IOException {
        MemberDTO memberDTO = new MemberDTO();
        Long loginId = (Long) session.getAttribute("memberId");
        memberDTO.setId(loginId);
        memberDTO.setMemberFile(memberFile);
        memberSerivce.profileUpdate(memberDTO);
        MemberDTO memberDTO1 = memberSerivce.findById((Long) session.getAttribute("memberId"));
        return new ResponseEntity<>(memberDTO1,HttpStatus.OK);
    }

    @GetMapping("/profileDelete")
    public String profileDelete(HttpSession session) {
        Long loginId = (Long) session.getAttribute("memberId");
        memberSerivce.profileDelete(loginId);
        return "redirect:/member/myPage/" + loginId;
    }
    @PostMapping("/profileDelete1")
    public ResponseEntity<?> profileDelete1(HttpSession session) {
        Long loginId = (Long) session.getAttribute("memberId");
        memberSerivce.profileDelete(loginId);
        MemberDTO memberDTO = memberSerivce.findById((Long) session.getAttribute("memberId"));
        return new ResponseEntity<>(memberDTO,HttpStatus.OK);
    }

    @GetMapping("/findPwAuth")
    public String findPwAuthForm() {
        return "memberPages/memberReset";
    }

    @GetMapping("/findPw")
    public String findPwForm(@RequestParam("email") String email, Model model) {
        System.out.println("email = " + email);
        model.addAttribute("email", email);
        return "memberPages/passwordUpdate";
    }

    @GetMapping("/findPwAuthMail")
    @ResponseBody
    public String findPwAuth(String email) {
        System.out.println("이메일 인증 요청이 들어옴!");
        System.out.println("이메일 인증 이메일 : " + email);
        return mailSendService.createMailForm2(email);
    }

    @PostMapping("/findPw")
    public String findPW(@RequestParam("email") String email,
                         @RequestParam("memberPassword") String memberPassword) {
        Long memberId = memberSerivce.findByMemberEmail(email);
        MemberDTO memberDTO = memberSerivce.findById(memberId);
        memberSerivce.changePassword(memberDTO, memberPassword);
        return "index";
    }

    @PostMapping("/checkPassword")
    public ResponseEntity<Map<String, Boolean>> checkPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String plainPassword = request.get("password");
        Long memberId = memberSerivce.findByMemberEmail(email);
        MemberDTO memberDTO = memberSerivce.findById(memberId);
        String orginPassword = memberDTO.getMemberPassword();

        boolean result = memberSerivce.checkPassword(plainPassword, orginPassword);
        System.out.println("result = " + result);

        Map<String, Boolean> response = new HashMap<>();
        response.put("result", result);

        return ResponseEntity.ok(response);
    }
  
    @GetMapping("/delete")
    public String delete(HttpSession session) {
        Long loginId = (Long) session.getAttribute("memberId");
        memberSerivce.delete(loginId);
        return "redirect:/member/logout";
    }

    @GetMapping("/popup")
    public String popup(HttpSession session, Model model) {
        MemberDTO memberDTO = memberSerivce.findById((Long) session.getAttribute("memberId"));
        model.addAttribute("memberDTO", memberDTO);
        return "memberPages/popup";
    }
    @PostMapping("/textUpdate")
    public ResponseEntity<?> textUpdate(@RequestBody MemberDTO memberDTO, HttpSession session) {
        MemberDTO upDTO = memberSerivce.findById((Long) session.getAttribute("memberId"));
        upDTO.setMemberText(memberDTO.getMemberText());
        upDTO.setMemberGender(memberDTO.getMemberGender());
        memberSerivce.memberUpdate(upDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/search")
    public ResponseEntity<?> memberSearch(@RequestBody Map<String, String> requestBody) {
        String q = requestBody.get("q");
        List<MemberDTO> memberDTOList = memberSerivce.searchMember(q);
        return new ResponseEntity<>(memberDTOList, HttpStatus.OK);
    }
    @PostMapping("/searchList")
    public ResponseEntity<?> memberSearchList(HttpSession session) {
        List<MemberDTO> memberDTOList = memberSerivce.searchFindAll((Long) session.getAttribute("memberId"));
        return new ResponseEntity<>(memberDTOList, HttpStatus.OK);
    }
    @PostMapping("/searchDelete")
    public ResponseEntity<?> searchDelete(@RequestBody Map<String, Long> requestBody, HttpSession session) {
        Long searchId = requestBody.get("searchId");
        memberSerivce.searchDelete(searchId, (Long) session.getAttribute("memberId"));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/searchDeleteAll")
    public ResponseEntity<?> searchDeleteAll(HttpSession session) {
        memberSerivce.searchDeleteAll((Long) session.getAttribute("memberId"));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
