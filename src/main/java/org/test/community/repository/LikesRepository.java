package org.test.community.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.test.community.model.Category;
import org.test.community.model.LikeTable;

@EnableJpaRepositories
public interface LikesRepository extends JpaRepository<LikeTable,Integer>{


	
}
