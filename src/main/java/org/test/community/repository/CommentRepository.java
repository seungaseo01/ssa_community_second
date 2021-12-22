package org.test.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.test.community.model.Comment;

@EnableJpaRepositories
public interface CommentRepository  extends JpaRepository<Comment,Integer>{

	@Query(value = "select c.cm_no, c.b_no , c.cm_seq, c.cm_grp , c.cm_content ,c.cm_writer,c.cm_regdate, u.username from comment c "
			+ "join `user` u on c.cm_writer = u.id "
			+ "where b_no = :bNo", nativeQuery = true)
	List<Comment> findByBNo(@Param("bNo") int bNo);
	
	
	@Query(value = "select max(cm_grp) as grp from comment where b_no = :bNo", nativeQuery = true)
	Integer getLastComment(int bNo);
	
}
