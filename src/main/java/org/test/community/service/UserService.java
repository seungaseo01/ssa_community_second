package org.test.community.service;

import java.util.Date;
import java.util.Random;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.test.community.model.Role;
import org.test.community.model.User;
import org.test.community.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
	JavaMailSender javaMailSender; 

    
 // 회원가입
 	public User insert(User user) {
 		
 		// 비밀번호 암호화
 		String encodedPassword = passwordEncoder.encode(user.getPassword()); 
 		user.setPassword(encodedPassword);
 		
 		// 활성화 여부
 		user.setEnabled(true);
 		
 		// 멤버 권한 설정
 		Role role = new Role();
 		role.setId(1);
 		user.getRoles().add(role);
 		
 		return userRepository.save(user);
 	}

 	// 회원 목록 조회(페이징)
 	public Page<User> getMemberList(Pageable pageable) {
 		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
 		pageable = PageRequest.of(page, 10, Sort.by("regDate"));
 		return userRepository.findAll(pageable);
 	}
 	
 	
 	
 	/* 회원 가입 시 */
 	// 아이디 중복 검사
 	public String usernameCheck(String username) {		
 		if(!userRepository.findByUsername(username).isEmpty()) {
 			return "exist";
 		}else {
 			return "notExist";
 		}
 	}
 	
 	// 닉네임 중복 검사
 	public String nicknameCheck(String nickname) {
 		
 		if(!userRepository.findByNickname(nickname).isEmpty()) {
 			return "exist";
 		}else {
 			return "notExist";
 		}
 	}

 	// 이메일 중복 검사
 	public String emailCheck(String email) {
 		
 		if(!userRepository.findByEmail(email).isEmpty()) {
 			return "exist";
 		}else {
 			return sendCode(email);
 		}
 	}
 	
 	
 	
 // 이메일 인증번호 전송
 	public String sendCode(String email) {
 		
 		System.out.println("이메일 전송 service impl ~~~~~~~~~~~~~~~~~~");
 		System.out.println("이메일 =============> " + email);
 		
 		// 인증번호 난수 생성
 		Random random = new Random(); 
 		int authCode = random.nextInt(899999) + 100000; // 100000 ~ 999999 범위의 난수 생성
 		System.out.println("인증번호 =============> " + authCode);
 		
 		String title = "커뮤니티 : 회원가입 인증";
 		String content = "커뮤니티를 이용해주셔서 감사합니다." + "<br><br>" + "인증번호는 <b>" + authCode + "</b> 입니다." + "<br><br>"
 				+ "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

 		
 		// 메일 발송
 		try {
 			MimeMessage message = javaMailSender.createMimeMessage();
 			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email, "UTF-8")); // 수신자 설정
 			message.setSubject(title); // 메일 제목
 			message.setText(content); // 메일 내용
 			javaMailSender.send(message); // 메일 발송
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		
 		String num = Integer.toString(authCode); // view단으로 보내기 위한 난수 String 파싱

 		return num;
 	}
 	
    

}
