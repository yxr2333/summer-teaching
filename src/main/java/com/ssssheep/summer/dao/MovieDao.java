package com.ssssheep.summer.dao;

import com.ssssheep.summer.pojo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.dao
 * @datetime 2022/7/29 星期五
 */
@Repository
public interface MovieDao extends JpaRepository<Movie, Integer> {

    /**
     * 随机读取4条电影的数据
     * @return 随机读取的数据
     */
    @Query(nativeQuery = true,value = "select * from t_movies order by RAND() limit 4")
    List<Movie> randomAccessMovies();
}
