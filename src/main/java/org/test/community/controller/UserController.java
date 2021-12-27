package org.test.community.controller;

import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.test.community.model.Category;
import org.test.community.model.Comment;
import org.test.community.model.TotalBoard;
import org.test.community.model.User;
import org.test.community.repository.BoardRepository;
import org.test.community.repository.CategoryRepository;
import org.test.community.repository.CommentRepository;
import org.test.community.repository.LikesRepository;
import org.test.community.repository.UserRepository;
import org.test.community.service.LikesService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BoardRepository boardRepository;
    
    @Autowired
    LikesService likesService;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    LikesRepository likesRepository;
    
    @Autowired
    CommentRepository commentRepository;
    

    
    
    //회원정보 수정페이지 이동    
    @GetMapping("/modForm")
    public String goModMyInfo(Model model, Principal principal) {
    	
   	 System.out.println("===============goModMyInfo===============");

    	
//   	 model.addAttribute("user",user.get());
//
//   	 System.out.println("========res=========="+user.get());
     
   	 return "user/modForm";
    }  
   
    


}
