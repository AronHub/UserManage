package com.fjt.repositery;

import org.springframework.data.repository.CrudRepository;

import com.fjt.pojo.Project;
import com.fjt.repositery.custom.ProjRrepCustom;

public interface ProjRrep extends CrudRepository<Project, Integer>,ProjRrepCustom{
    
	 
}
