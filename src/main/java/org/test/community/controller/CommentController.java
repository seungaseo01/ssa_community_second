package org.test.community.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.test.community.model.Comment;
import org.test.community.model.TotalBoard;
import org.test.community.model.User;
import org.test.community.repository.CommentRepository;
import org.test.community.repository.UserRepository;
import org.test.community.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentService commentService;
	
	// 댓글 추가
	@PostMapping("/insert")
	@ResponseBody
	private int commentInsert(@RequestBody HashMap <String, Object> comment, Principal principal) {


		
		 System.out.println("=============commentInsert================");
    	 String username = principal.getName();
    	 

    	 System.out.println("=============username================"+username);  	 
    	 int id = userRepository.selectId(username); 
    	 
    	 System.out.println("================id=========="+id);
		

 		System.out.println("============comment=============="+comment);
    	 
 		
 		// 객체 생성 후 담기 
 		Comment insertComment =  new Comment();
 		
 		Optional<Integer> grp = commentService.getgrp(Integer.parseInt(comment.get("bNo").toString()));

 		System.out.println("=======grp========="+grp);
 		
 		insertComment.setCmContent(comment.get("cmContent").toString());
 		insertComment.setCmGrp(grp.get());
 		insertComment.setCmSeq(1);
		System.out.println("===========insertComment1============="+insertComment);
		
		User user = new User();
		user.setId(id);	
		insertComment.setUser(user);
		System.out.println("===========insertComment2============="+insertComment);
		
		TotalBoard board = new TotalBoard();		
		board.setBNo(Integer.parseInt(comment.get("bNo").toString()));
		insertComment.setBoard(board);

		
		System.out.println("===========insertComment3============="+insertComment);
		
		
		Comment res = commentRepository.save(insertComment);
		
		System.out.println("============res====="+res.toString());
		
		return board.getBNo();
	}	

	
	@GetMapping("/selectComment/{bNo}")
	private String commentSelect(@PathVariable("bNo") int bNo, Model model) {

		System.out.println("=============selectComment================");		

 		System.out.println("============bNo=============="+bNo);
    	 

		List<Comment> comments =  commentRepository.findByBNo(bNo);
 		
 		System.out.println("===========comments============="+comments.toString());

 		model.addAttribute("comments",comments);
 		

		return "board/comment";
	}	
	
	
	
	
	// 대댓글 추가
	@PostMapping("/reInsert")
	@ResponseBody
	private int reCommentInsert(@RequestBody HashMap <String, Object> comment, Principal principal) {


		System.out.println("=============commentInsert================");
    	String username = principal.getName();
    	 
    	System.out.println("=============username================"+username);  	 
    	int id = userRepository.selectId(username); 
    	 
    	System.out.println("================id=========="+id);
 		System.out.println("============comment=============="+comment);
    	 
 		
 		// 객체 생성 후 담기 
 		Comment insertComment =  new Comment();
 		insertComment.setCmContent(comment.get("cmContent").toString());
 		
 		Optional<Integer> grp = commentService.getgrp(Integer.parseInt(comment.get("bNo").toString()));
 		
 	 	insertComment.setCmGrp(grp.get()); 		
 	 	insertComment.setCmSeq(1);



		System.out.println("===========insertComment1============="+insertComment);
		
		User user = new User();
		user.setId(id);	
		insertComment.setUser(user);
		System.out.println("===========insertComment2============="+insertComment);
		
		TotalBoard board = new TotalBoard();		
		board.setBNo(Integer.parseInt(comment.get("bNo").toString()));
		insertComment.setBoard(board);

		
		System.out.println("===========insertComment3============="+insertComment);
		
		
		Comment res = commentRepository.save(insertComment);
		
		System.out.println("============res====="+res.toString());
		
		return board.getBNo();
	}	

	
	
}
