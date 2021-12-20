package org.test.community.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.community.model.LikeTable;
import org.test.community.model.User;
import org.test.community.repository.LikesRepository;
import org.test.community.repository.UserRepository;

@Service
public class LikesService {

	@Autowired
	LikesRepository likesRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public void pushLike(int bNo, int id) {
		
		Optional<Integer> test =  Optional.ofNullable(likesRepository.checkLikes(bNo, id));
		
		
		if(test.isEmpty()) {
			likesRepository.likes(bNo, id);	
		}else { likesRepository.unlikes(bNo, id); }
		 
		System.out.println("like====================================="+test);
		

		
	}

	
}
