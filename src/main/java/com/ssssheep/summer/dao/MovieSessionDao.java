package com.ssssheep.summer.dao;

import com.ssssheep.summer.pojo.entity.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.dao
 * @datetime 2022/7/31 Sunday
 */
@Repository
public interface MovieSessionDao extends JpaRepository<MovieSession, Integer> {

    /**
     * 获取电影对应的场次信息
     * @param id 电影编号
     * @return 所有场次信息
     */
    @Query("select m from MovieSession m where m.movie.id = ?1")
    List<MovieSession> getAllByMovieId(Integer id);

    /**
     * 售票
     * @param sid 场次编号
     */
    @Modifying
    @Query("update MovieSession m set m.votes = m.votes - 1 where m.id = ?1")
    void sellTicket(Integer sid);

    /**
     * 退票
     * @param sid 场次编号
     */
    @Modifying
    @Query("update MovieSession m set m.votes = m.votes + 1 where m.id = ?1")
    void returnTicket(Integer sid);
}
