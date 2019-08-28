
/**  
 * @Title: UserRoleRepo.java
 * @Package com.fjt.repositery
 * @Description: TODO(用一句话描述该文件做什么)
 * @author fujiantao
 * @date 2019年8月19日 下午2:46:57 
 * @version V1.0  
 */

package com.fjt.repositery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fjt.pojo.UserRole;

/**
     * @ClassName: UserRoleRepo
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @author fujiantao
     * @date 2019年8月19日
     *
     */

public interface UserRoleRepo extends JpaRepository<UserRole, Integer> {

}
