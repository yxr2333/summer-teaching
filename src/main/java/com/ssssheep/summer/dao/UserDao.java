package com.ssssheep.summer.dao;

import com.ssssheep.summer.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.dao
 * @datetime 2022/7/31 Sunday
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    /**
     * 通过用户名查询用户信息
     * @param name 用户名
     * @return 用户信息
     */
    User getUserByName(String name);

    /**
     * 通过用户名判断用户是否存在
     * @param name 用户名
     * @return 是否存在
     */
    Boolean existsByName(String name);
}
