package com.example.finalproject.controller;

import com.example.finalproject.dto.MemberDTO;
import com.example.finalproject.entitiy.MemberEntity;
import com.example.finalproject.kakaoDTO.KakaoProfile;
import com.example.finalproject.kakaoDTO.OAuthToken;
import com.example.finalproject.repository.MemberRepository;
import com.example.finalproject.serivce.MemberSerivce;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class KakaoController {
    @Value("${cos.key}")
    private String cosKey;

    private final MemberSerivce memberSerivce;

    private final MemberRepository memberRepository;

    private final AuthenticationManager authenticationManager;

    @GetMapping("/kakao/login")
    public String kakaoCallBack(String code, HttpSession session) {

        // POST 방식으로 key=value 데이터를 요청(카카오 쪽으로)
        RestTemplate rt = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "483c5fda5520fe4ac417eb418b63af8b");
        params.add("redirect_uri", "http://localhost:8090/kakao/login");
        params.add("code", code);

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoRequest = new HttpEntity<>(params, headers);

        // Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token",
                HttpMethod.POST, // 요청 메서드
                kakaoRequest, // 데이터
                String.class // 응답 받을 타입
                );

        // Gson, Json Simple, ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = null;

        try {
            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("카카오 엑세스 토큰 = " + oAuthToken.getAccess_token());

        RestTemplate rt2 = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

        // Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
                kakaoProfileRequest2, String.class);
        System.out.println(response2.getBody());

        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;

        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Member 오브젝트 : username, password, email
        System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
        System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());

        System.out.println("유저네임 : "+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
        System.out.println("이메일 : "+kakaoProfile.getKakao_account().getEmail());
        System.out.println("생일 : " + kakaoProfile.getKakao_account().getBirthday());
        // UUID란 -> 중복되지 않는 어떤 특정 값을 만들어내는 알고리즘

        MemberEntity kakaoMember = MemberEntity.createKakaoMember(kakaoProfile, cosKey);

        kakaoMember.setMemberPassword(cosKey);

        System.out.println("Password = " + kakaoMember.getMemberPassword());


        // 가입자 혹은 비가입자 체크 해서 처리
        boolean originMember = memberSerivce.mailCheck(kakaoMember.getMemberEmail());
        Long savedMember = null;

        if(!originMember) {
            System.out.println("카카오" + MemberDTO.toDTO(kakaoMember));
            System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다");
            savedMember = memberSerivce.save(MemberDTO.toDTO(kakaoMember));
        } else {
            savedMember = memberSerivce.findByMemberEmail(kakaoMember.getMemberEmail());
        }


        System.out.println("자동 로그인을 진행합니다.");
        // 로그인 처리
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(memberSerivce.findById(savedMember).getMemberEmail(), cosKey));
        System.out.println("authentication = " + authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        session = attributes.getRequest().getSession();
        session.setAttribute("memberId", savedMember);
        session.setAttribute("accessToken", oAuthToken.getAccess_token()); // 액세스 토큰 저장


        System.out.println(kakaoMember.getId());
        System.out.println(MemberDTO.toDTO(kakaoMember));


        System.out.println("kakaoSession = "+session.getAttribute("memberId"));

        return "redirect:/board/main";
    }


    }

