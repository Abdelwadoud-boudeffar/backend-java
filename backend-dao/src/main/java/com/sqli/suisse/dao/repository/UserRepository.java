package com.sqli.suisse.dao.repository;

import com.sqli.suisse.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 
 * @author ABDELWADOUD BOUDEFFAR
 * @since 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsUserByUsername(String username);

}
