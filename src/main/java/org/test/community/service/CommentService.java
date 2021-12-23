package org.test.community.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.community.repository.CommentRepository;

@Service
public class CommentService {

	
	@Autowired
	CommentRepository commentRepository;
	
	public Optional<Integer> getgrp(int bNo) {
		
		Optional<Integer> res =  Optional.ofNullable(commentRepository.getLastComment(bNo));
	
		if(res.isEmpty()) {
			res = Optional.of(1);
		}else {
			res= Optional.of(res.get()+1);
		}
		
		return res;
		
	}
	
	public Optional<Integer> getSeq(int bNo, int cmGrp) {
		
		Optional<Integer> res =  Optional.ofNullable(commentRepository.getLastCommentSeq(bNo, cmGrp));
		
		return Optional.of(res.get()+1);
		
	}

}
