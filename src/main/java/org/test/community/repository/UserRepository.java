package org.test.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.community.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {


}
