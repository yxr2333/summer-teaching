package com.ssssheep.summer.dao;

import com.ssssheep.summer.pojo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.dao
 * @datetime 2022/7/29 星期五
 */
@Repository
public interface MovieDao extends JpaRepository<Movie, Integer> {
}
