package com.ssssheep.summer.service.impl;

import com.ssssheep.summer.dao.MovieDao;
import com.ssssheep.summer.dao.MovieSessionDao;
import com.ssssheep.summer.pojo.dto.ApiResult;
import com.ssssheep.summer.pojo.dto.MovieSessionDTO;
import com.ssssheep.summer.pojo.entity.Movie;
import com.ssssheep.summer.pojo.entity.MovieSession;
import com.ssssheep.summer.service.MovieService;
import com.ssssheep.summer.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.service.impl
 * @datetime 2022/7/29 星期五
 */
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    final MovieDao movieDao;
    final MovieSessionDao movieSessionDao;
    final Utils<Movie> utils;

    /**
     * 获取所有的电影信息
     *
     * @return 所有的电影信息
     */
    @Override
    public ApiResult getAllMovies(Integer pageNum, Integer pageSize) {
        return utils.makePageData(pageNum, pageSize, Movie.class);
    }

    /**
     * 根据编号获取电影信息
     *
     * @param uid 电影编号
     * @return 请求结果
     */
    @Override
    public ApiResult getOne(Integer uid) {
        Movie movie = movieDao.findById(uid).orElseThrow(() -> new RuntimeException("不存在的电影编号"));
        return ApiResult.success(movie);
    }

    /**
     * 根据电影编号获取场次信息
     *
     * @param uid 编号
     * @return 请求结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResult getSessionsByMovieId(Integer uid) {
        if(!movieDao.existsById(uid)){
            throw new RuntimeException("不存在的电影编号");
        }
        List<MovieSession> movieSessions = movieSessionDao.getAllByMovieId(uid);
        if(movieSessions.isEmpty()){
            throw new RuntimeException("该电影今日暂未排场");
        }
        List<MovieSessionDTO> movieSessionDTOList = movieSessions.stream().map(movieSession -> {
            MovieSessionDTO movieSessionDTO = new MovieSessionDTO();
            movieSessionDTO.setId(movieSession.getId());
            movieSessionDTO.setPrice(movieSession.getPrice());
            movieSessionDTO.setVotes(movieSession.getVotes());
            movieSessionDTO.setStartTime(movieSession.getStartTime());
            return movieSessionDTO;
        }).collect(Collectors.toList());
        return ApiResult.success(movieSessionDTOList);
    }
}
