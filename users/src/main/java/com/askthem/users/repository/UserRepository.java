package com.askthem.users.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.askthem.users.entiry.Users;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	
	Users findByUsername(String username);
	
	@Modifying
	@Query("UPDATE Users u SET u.accesstoken = :aToken WHERE u.username = :username")
	int updateUserWithToken(String username, String aToken);
	
//	@Modifying
//	@Query("update Users u set u.accesstoken = ?2 where u.username = ?1")
//	void updateUserWithToken(String username, String aToken);
	
//	@Query("SELECT * FROM users WHERE user_name IS :userName")
//	List<User> findByUserName(@Param("userName") String userName);
}
