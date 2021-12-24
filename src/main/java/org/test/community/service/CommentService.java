package org.test.community.service;

import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.community.repository.CommentRepository;

@Service
public class CommentService {

	
	@Autowired
	CommentRepository commentRepository;
	
	//댓글 추가시 getgrp 
	public Optional<Integer> getgrp(int bNo) {
		
		Optional<Integer> res =  Optional.ofNullable(commentRepository.getLastComment(bNo));
	
		if(res.isEmpty()) {
			res = Optional.of(1);
		}else {
			res= Optional.of(res.get()+1);
		}
		
		return res;
		
	}
	
	//대댓글 추가시 getSeq
	public Optional<Integer> getSeq(int bNo, int cmGrp) {
		
		Optional<Integer> res =  Optional.ofNullable(commentRepository.getLastCommentSeq(bNo, cmGrp));
		
		return Optional.of(res.get()+1);
		
	}

	
	//댓글 삭제시
	public int deleteComment(int bNo,int cmNo) {
		String isParentComment = commentRepository.getCommentSeq(cmNo);
		System.out.println("========isParentComment============"+isParentComment);
		
		int result = 0;
		
		if(isParentComment.equals("true")) {
			int cmGrp = commentRepository.getCommentGrp(cmNo);
			
			System.out.println("부모댓글임");
			System.out.println("========getCommentGrp============"+cmGrp);
			
			commentRepository.deleteByGrp(bNo, cmGrp);
			result = 1;
		}else {
			
			System.out.println("자식댓글임");
			commentRepository.deleteById(cmNo);
			result = 1;
		}
		
		return result;
	}
	
	
}
