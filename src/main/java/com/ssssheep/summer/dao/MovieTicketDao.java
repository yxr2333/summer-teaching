package com.ssssheep.summer.dao;

import com.ssssheep.summer.pojo.entity.MovieTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.dao
 * @datetime 2022/7/31 Sunday
 */
@Repository
public interface MovieTicketDao extends JpaRepository<MovieTicket, Integer>, JpaSpecificationExecutor<MovieTicket> {

    /**
     * 查询用户的购买记录
     *
     * @param uid 购买人编号
     * @param pageable 分页条件
     * @return 购买记录
     */
    @Query("select m from MovieTicket m where m.buyer.uid = ?1 order by m.id DESC")
    Page<MovieTicket> findAllByBuyerUidOrderByIdDesc(Integer uid, Pageable pageable);
}
