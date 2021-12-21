package org.test.community.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.test.community.model.Comment;
import org.test.community.model.User;
import org.test.community.repository.CommentRepository;
import org.test.community.repository.UserRepository;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UserRepository userRepository;

	/*
	 * @GetMapping("/writer")
	 * 
	 * @ResponseBody private int selectUser(@RequestBody String user) {
	 * 
	 * System.out.println("==========user=========="+user);
	 * 
	 * int res = userRepository.selectId(user);
	 * 
	 * return res; }
	 */
	
	
	@PostMapping("/insert")
	@ResponseBody
	private String commentInsert(@RequestBody Comment comment, Principal principal) {

		
		 System.out.println("=============commentInsert================");
    	 String username = principal.getName();
    	 

    	 System.out.println("=============username================"+username);  	 
    	 int id = userRepository.selectId(username); 
    	 
    	 System.out.println("================id=========="+id);
		
	
		User user = new User();
		
		user.setId(id);
		
		comment.setUser(user);
		

		System.out.println("============comment=============="+comment);
		
		
//		Comment res = commentRepository.save(comment);
		
//		System.out.println(res);
		return "";
	}	
	
}
