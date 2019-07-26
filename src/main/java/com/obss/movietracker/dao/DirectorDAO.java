package com.obss.movietracker.dao;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.obss.movietracker.models.Director;


@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface DirectorDAO extends JpaRepository<Director,Long>{
	@Query("select u FROM Director u WHERE name = ?1")
	List<Director> findDirectorByName(String name);

	@Query("select u FROM Director u WHERE name LIKE %?1% ")
	List<Director> findDirectorLike(String name);
}
