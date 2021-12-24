package org.test.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.test.community.model.Comment;

@EnableJpaRepositories
public interface CommentRepository  extends JpaRepository<Comment,Integer>{

	@Query(value = "select * "
			+ "from comment c join `user` u "
			+ "on c.cm_writer = u.id "
			+ "where b_no = :bNo "
			+ "order by c.cm_grp, c.cm_seq", nativeQuery = true)
	List<Comment> findByBNo(@Param("bNo") int bNo);
	
	
	@Query(value = "select max(cm_grp) as grp from comment where b_no = :bNo", nativeQuery = true)
	Integer getLastComment(int bNo);
	
	@Query(value = "select max(cm_seq) as grp from comment where b_no = :bNo and cm_grp = :cmGrp", nativeQuery = true)
	Integer getLastCommentSeq(int bNo, int cmGrp);
	
	@Query(value = "select if(c.cm_seq<=1,'true','false') as result "
			+ "from comment c where cm_no = :cmNo", nativeQuery = true)
	String getCommentSeq(int cmNo);
	
	@Query(value = "select cm_grp from comment where cm_no = :cmNo", nativeQuery = true)
	Integer getCommentGrp(int cmNo);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM comment WHERE b_no = :bNo and cm_grp = :cmGrp", nativeQuery = true)
	void deleteByGrp(int bNo,int cmGrp);
	

	
}
