package org.test.community.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.test.community.model.User;
import org.test.community.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/register")
    public String register() {
        return "account/register";
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.insert(user);
        return "redirect:/";
    }

    
    // 아이디 중복 검사
 	@PostMapping("/register/usernameCheck")
 	@ResponseBody
 	public String usernameCheck(String username) {
 		System.out.println("====validationController 아이디 중복 검사 진입==========");
 		
 		return userService.usernameCheck(username);
 	}
 	
 	// 닉네임 중복 검사
 	@PostMapping("/register/nicknameCheck")
 	@ResponseBody
 	public String nicknameCheck(String nickname) {
 		System.out.println("====validationController 닉네임 중복 검사 진입==========");
 		
 		return userService.nicknameCheck(nickname);
 	}
 	
 	// 이메일 중복 검사
 	@PostMapping("/register/emailCheck")
 	@ResponseBody
 	public String emailCheck(String email) {
 		System.out.println("====validationController 이메일 중복 검사 진입==========");
 		
 		return userService.emailCheck(email);
 	}
 	

    
}
