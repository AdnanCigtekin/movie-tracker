package com.obss.movietracker.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.obss.movietracker.models.Movie;


@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface MovieDAO extends JpaRepository<Movie,Long>{
	@Query("select u FROM Movie u WHERE name = ?1")
	List<Movie> findMovieByName(String name);

	@Query("select u FROM Movie u WHERE (name = ?1 and release_date = ?2 )")
	List<Movie> findMovieByNameAndDate(String name, Date date);
	
	
	@Query("select u FROM Movie u WHERE name LIKE %?1% ")
	List<Movie> findMyMovieLike(String name);
}
