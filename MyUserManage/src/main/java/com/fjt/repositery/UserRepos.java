package com.fjt.repositery;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.fjt.pojo.User;
import com.fjt.repositery.custom.PageCustom;
import com.fjt.repositery.custom.UserReporsCustom;

//JpaRepository接口提供了分页实现的方法findAll(Pageable pageable)
public interface UserRepos extends JpaRepository<User, Integer>,UserReporsCustom,PageCustom{

	@Query("select us from User us where us.name=:username")
	public User findUser(@Param("username")String  username);
	
	@Query(value="select us from User us")
	public List<User> findAllUser(); 
	
	
	
	
	
	
	
	
	
	
//	@Query(value=" select t1.*,rownum rw from (select us from User us)t1 where rownum<=(pageRows*pageNow) ",nativeQuery=true)
//	public List<User> findPage(@Param("pageRows")int pageRows,@Param("pageNow")int pageNow);
    
//	@Query(value="select t1.*,rownum rw from (select us from User us)t1 where rownum<=3",nativeQuery=true)
//	public List<User> findPageUser();

	
}
