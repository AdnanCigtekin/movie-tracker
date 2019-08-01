package com.obss.movietracker.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.obss.movietracker.models.Lists;
import com.obss.movietracker.models.Role;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface RoleDAO extends JpaRepository<Role,Long>{
	@Query("select u FROM Role u WHERE name = ?1")
	List<Role> findRoleByName(String name); 

}
