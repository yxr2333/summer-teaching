package com.ssssheep.summer.service.impl;

import com.ssssheep.summer.dao.MovieDao;
import com.ssssheep.summer.dao.MovieSessionDao;
import com.ssssheep.summer.dao.MovieTicketDao;
import com.ssssheep.summer.dao.UserDao;
import com.ssssheep.summer.pojo.dto.ApiResult;
import com.ssssheep.summer.pojo.entity.Movie;
import com.ssssheep.summer.pojo.entity.MovieSession;
import com.ssssheep.summer.pojo.entity.MovieTicket;
import com.ssssheep.summer.pojo.entity.User;
import com.ssssheep.summer.pojo.vo.BuyTicketParam;
import com.ssssheep.summer.pojo.vo.ReturnTicketParam;
import com.ssssheep.summer.service.MovieTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.service.impl
 * @datetime 2022/7/31 Sunday
 */
@Service
@RequiredArgsConstructor
public class MovieTicketServiceImpl implements MovieTicketService {

    final MovieTicketDao movieTicketDao;
    final MovieDao movieDao;
    final UserDao userDao;
    final MovieSessionDao movieSessionDao;

    private static final int MAX_OVERTIME_MINUTES = 30;

    /**
     * 购买电影票
     *
     * @param param 参数
     * @return 购买结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResult buyTicket(BuyTicketParam param) {
        MovieTicket ticket = new MovieTicket();
        MovieSession movieSession = movieSessionDao
                .findById(param.getMovieSessionId())
                .orElseThrow(() -> new RuntimeException("不存在此场次编号"));
        if(movieSession.getVotes() <= 0) {
            throw new RuntimeException("该场次票已售完,购买失败");
        }
        if(!movieSession.getMovie().getId().equals(param.getMovieId())) {
            throw new RuntimeException("场次和电影信息不匹配,购买失败");
        }
        Movie movie = movieDao
                .findById(param.getMovieId())
                .orElseThrow(() -> new RuntimeException("不存在此电影编号"));
        User buyer = userDao
                .findById(param.getBuyerId())
                .orElseThrow(() -> new RuntimeException("不存在此用户编号"));
        synchronized (this) {
            movieSessionDao.sellTicket(param.getMovieSessionId());
        }
        // 设置电影票的参数
        ticket.setMovie(movie);
        ticket.setBuyer(buyer);
        ticket.setCinema(param.getCinema());
        ticket.setName(movie.getName());
        ticket.setPrice(param.getPrice());
        ticket.setStartTime(param.getStartTime());
        // 保存到数据库
        movieTicketDao.save(ticket);
        return ApiResult.success("购票成功");
    }

    /**
     * 退票
     *
     * @param param 参数
     * @return 退票结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResult returnTicket(ReturnTicketParam param) {
        if(!movieSessionDao.existsById(param.getMovieSessionId())){
            throw new RuntimeException("不存在的场次编号");
        }
        MovieTicket ticket = movieTicketDao
                .findById(param.getOrderId())
                .orElseThrow(() -> new RuntimeException("不存在此票据信息,请检查是否重复退票"));
        if(!ticket.getBuyer().getUid().equals(param.getReturnerId())) {
            throw new RuntimeException("该票不是您所购买,无法退票");
        }
        synchronized (this) {
            movieSessionDao.returnTicket(param.getMovieSessionId());
        }
        movieTicketDao.deleteById(param.getOrderId());
        return ApiResult.success("退票成功");
    }
}
