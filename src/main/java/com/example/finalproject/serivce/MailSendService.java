package com.example.finalproject.serivce;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Component
@Service
@RequiredArgsConstructor
public class MailSendService {
    // 의존성 주입
    private final JavaMailSender mailSender;

    private int authNum; // 인증 번호

    // 난수 생성 메소드
    public void createAuthCode() {
        // 난수의 범위 111111 ~ 999999 (6자리 난수)
        Random r = new Random();
        int checkNum = r.nextInt(888888) + 111111;
        System.out.println("인증번호 : " + checkNum);
        authNum = checkNum;
    }
    // 보낼 메일의 양식 만드는 메소드
    public String createMailForm(String email) {
        createAuthCode();
        String setFrom = "fluer7123@gmail.com";
        String toMail = email;
        String title = "회원 가입 인증 이메일 입니다."; // 이메일 제목
        String content =
                "안녕하세요" + 	//html 형식으로 작성 !
                        "<br><br>" +
                        "인증 번호는 " + authNum + "입니다." +
                        "<br>" +
                        "해당 인증번호를 인증번호 확인란에 기입하여 주세요."; //이메일 내용 삽입
        sendMail(setFrom, toMail, title, content);
        return Integer.toString(authNum);
    }

    public String createMailForm2(String email) {
        createAuthCode();
        String setFrom = "fluer7123@gmail.com";
        String toMail = email;
        String title = "비밀번호 재설정 인증 이메일입니다";
        String content =
                "안녕하세요" +
                        "<br><br>" +
                        "인증 번호는 " + authNum + "입니다." +
                        "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
        sendMail(setFrom, toMail, title, content);
        return Integer.toString(authNum);
    }

    // 메일을 보내는 메소드
    public void sendMail(String setFrom, String toMail, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        // true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            // true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
            helper.setText(content,true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}
