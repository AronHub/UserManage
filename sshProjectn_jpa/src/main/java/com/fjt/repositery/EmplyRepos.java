package com.fjt.repositery;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fjt.pojo.Emplye;
import com.fjt.repositery.custom.EmplyCustom;

public interface EmplyRepos extends CrudRepository<Emplye, Integer>,EmplyCustom{

	@Modifying
	@Query("delete from Emplye em where em.id=:id") 
	public int detl(@Param("id")int id); 
	
	@Modifying
	@Query("delete from Emplye em")
	public void deltAll();
	
	
	@Query("select em from Emplye em where em.id=:id")
	public Emplye serchByid(@Param("id") int id);
	
}
