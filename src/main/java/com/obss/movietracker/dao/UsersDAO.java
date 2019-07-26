package com.obss.movietracker.dao;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.obss.movietracker.models.Users;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface UsersDAO extends JpaRepository<Users,Long>{
	
	@Query("select u FROM Users u WHERE username = ?1 and password=?2")
	List<Users> findMyUser(String name,String password);
	
	@Query("select u FROM Users u WHERE username = ?1")
	List<Users> findMyUserByUsername(String name);
	
	@Query("select u FROM Users u WHERE username LIKE %?1% ")
	List<Users> findMyUsernameLike(String name);
	
	
}
