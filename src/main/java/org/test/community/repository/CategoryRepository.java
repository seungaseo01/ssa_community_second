package org.test.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.test.community.model.Category;


@EnableJpaRepositories
public interface CategoryRepository extends JpaRepository<Category,Integer> {
// MyBatis 쿼리문 대신 들어갈 명령어 모음집.
//ex) save(),findById(),delete() 등등

}
