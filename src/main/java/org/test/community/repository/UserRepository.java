package org.test.community.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.transaction.annotation.Transactional;
import org.test.community.model.LikeTable;
import org.test.community.model.User;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {

	// username으로 Id찾기
	@Query(value = "select id from user where username = :username",nativeQuery = true)
	int selectId(@Param("username") String username);
	
	//회원목록
	@Query(value = "select id ,username ,email ,phone ,regdate ,nickname,enabled,password,max(role_id) as myrole "
			+ "from `user` u join user_role ur on u.id = ur.user_id "
			+ "group by u.id ",nativeQuery = true)
	List<User> manageUserList(Pageable pageable);
	
	//권한주기 (수정필요)
	@Transactional
	@Modifying
	@Query(value = "insert into user_role value(:id,2)", nativeQuery = true)
	void getAuthorization(int id);

	// 아이디 중복 검사
	List<User> findByUsername(String username);
	
	// 닉네임 중복 검사
	List<User> findByNickname(String nickname);
	
	// 이메일 중복 검사
	List<User> findByEmail(String email);

	
}
