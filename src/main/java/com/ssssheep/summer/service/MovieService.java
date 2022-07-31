package com.ssssheep.summer.service;

import com.ssssheep.summer.pojo.dto.ApiResult;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.service
 * @datetime 2022/7/29 星期五
 */
public interface MovieService {

    /**
     * 获取所有的电影信息
     * @param pageNum 页码
     * @param pageSize 页容量
     * @return 请求结果
     */
    ApiResult getAllMovies(Integer pageNum, Integer pageSize);

    /**
     * 根据编号获取电影信息
     * @param uid 电影编号
     * @return 请求结果
     */
    ApiResult getOne(Integer uid);

    /**
     * 根据电影编号获取场次信息
     * @param uid 编号
     * @return 请求结果
     */
    ApiResult getSessionsByMovieId(Integer uid);
}
