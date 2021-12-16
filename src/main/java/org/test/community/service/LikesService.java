package org.test.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.community.repository.LikesRepository;
import org.test.community.repository.UserRepository;

@Service
public class LikesService {

	@Autowired
	LikesRepository likesRepository;
	
	@Autowired
	UserRepository userRepository;
	

	
}
