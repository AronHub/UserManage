package com.fjt.repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fjt.pojo.User;
import com.fjt.repositery.custom.PageCustom;
import com.fjt.repositery.custom.UserReporsCustom;

public interface UserRepos
		extends JpaRepository<User, Integer>, UserReporsCustom, PageCustom {

	@Query("select us from User us where us.name=:username and us.pssword=:pssword")
	User findUser(@Param("username") String username,
			@Param("pssword") String passwd);

	@Query(value = "select us from User us")
	List<User> findAllUser();

	@Query("select us from User us where us.name=:username and us.telep=:telep")
	List<User> getUserBynameAndTelp(@Param("username") String userName,
			@Param("telep") String telep);

}
