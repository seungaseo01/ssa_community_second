package org.test.community.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.test.community.model.Category;
import org.test.community.model.User;
import org.test.community.repository.CategoryRepository;
import org.test.community.repository.UserRepository;



@Controller
public class HomeController {

	
    @Autowired
    CategoryRepository categoryRepository;
    
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String index(){
        return "index/index";
    }

    @GetMapping("/regCategoryForm")
    public String regCategory(){
        return "board/regCategoryForm";
    }

    @GetMapping("/regBoardForm")
    public String regBoard(Model model){

        List<Category> categoryList = categoryRepository.findAll();

        model.addAttribute("categoryList",categoryList);

        return "board/regBoardForm";
    }
    
    // 마이페이지 이동
    @GetMapping("/user/mypage")
    public String goMypage(Principal principal, Model model) {
    	
     System.out.println("========goMypage===========");  	
   	 
     String username = principal.getName();
	 
   	 System.out.println("=============username================"+username);  	 
   	 int id = userRepository.selectId(username); 
   	 
   	 System.out.println("=========id======="+id);
   	 
   	 Optional<User> user = userRepository.findById(id);
    	
   	 model.addAttribute("user",user.get());

   	 System.out.println("========res=========="+user.get());
        return "user/mypage";
    }
}
