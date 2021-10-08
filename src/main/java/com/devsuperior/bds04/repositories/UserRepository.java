package com.devsuperior.bds04.repositories;

import com.devsuperior.bds04.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   @Transactional(readOnly = true)
   User findByEmail(String email);
}
