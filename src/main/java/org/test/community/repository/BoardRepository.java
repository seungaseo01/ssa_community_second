package org.test.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;
import org.test.community.model.TotalBoard;



@EnableJpaRepositories
public interface BoardRepository extends JpaRepository<TotalBoard,Integer> {
// MyBatis 쿼리문 대신 들어갈 명령어 모음집.
//ex) save(),findById(),delete() 등등

    @Transactional
    @Modifying
    @Query("update TotalBoard set bViewcnt = bViewcnt + 1 where bNo = :bNo")
    int updateView(int bNo);
	
}
