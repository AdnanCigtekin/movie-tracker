package com.obss.movietracker.dao;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.obss.movietracker.models.Lists;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface MovieListDAO extends JpaRepository<Lists,Long>{
	@Query("select u FROM Lists u WHERE listName = ?1")
	List<Lists> findMovieListByName(String name);

}