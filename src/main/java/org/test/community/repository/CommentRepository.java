package org.test.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.test.community.model.Comment;

@EnableJpaRepositories
public interface CommentRepository  extends JpaRepository<Comment,Integer>{

}
