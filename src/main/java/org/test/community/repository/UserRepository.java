package org.test.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.test.community.model.LikeTable;
import org.test.community.model.User;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {



	@Query(value = "select id from user where username = :username",nativeQuery = true)
	int selectId(@Param("username") String username);
	
	
}
