package org.test.community.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
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
	
	@Query(value = "select id ,username ,email ,phone ,regdate ,nickname,enabled,password,max(role_id) as myrole "
			+ "from `user` u join user_role ur on u.id = ur.user_id "
			+ "group by u.id ",nativeQuery = true)
	List<User> manageUserList(Pageable pageable);
	
	
}
