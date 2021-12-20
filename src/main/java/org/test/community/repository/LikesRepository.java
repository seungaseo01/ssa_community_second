package org.test.community.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.test.community.model.Category;
import org.test.community.model.LikeTable;

@EnableJpaRepositories
public interface LikesRepository extends JpaRepository<LikeTable,Integer>{


	@Transactional
	@Modifying
	@Query(value = "insert into like_table(b_no,user_id) values(:bNo, :id)",nativeQuery = true)
	public void likes(@Param("bNo") int bNo, @Param("id")  int id);
	
	@Transactional
	@Modifying
	@Query(value = "delete from like_table where b_no = :bNo and user_id = :id",nativeQuery = true)
	public void unlikes(@Param("bNo") int bNo, @Param("id") int id);
	
	@Query(value = "select id from like_table where b_no = :bNo and user_id = :id",nativeQuery = true)
	public Integer checkLikes(@Param("bNo") int bNo, @Param("id") int id);
	

	@Query(value = "SELECT COUNT(id) FROM like_table where b_no = :bNo",nativeQuery = true)
	int selectLikes(@Param("bNo") int bNo);

	
}
