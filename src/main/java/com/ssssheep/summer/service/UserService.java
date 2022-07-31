package com.ssssheep.summer.service;

import com.ssssheep.summer.pojo.dto.ApiResult;
import com.ssssheep.summer.pojo.entity.User;
import com.ssssheep.summer.pojo.vo.CreateUserParam;
import com.ssssheep.summer.pojo.vo.UserLoginParam;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.service
 * @datetime 2022/7/31 Sunday
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param param 登录参数
     * @return 请求结果
     */
    ApiResult doLogin(UserLoginParam param);

    /**
     * 添加用户/用户注册
     *
     * @param param 参数
     * @return 请求结果
     */
    ApiResult addOne(CreateUserParam param);

    /**
     * 删除用户
     *
     * @param uid 用户编号
     * @return 结果
     */
    ApiResult deleteOne(Integer uid);

    /**
     * 更新用户信息
     *
     * @param user 更新后的用户信息
     * @return 请求结果
     */
    ApiResult updateOne(User user);

    /**
     * 查询所有用户的信息
     *
     * @param pageNum  页码
     * @param pageSize 页容量
     * @return 请求结果
     */
    ApiResult getAll(Integer pageNum, Integer pageSize);

    /**
     * 查询某个用户的信息
     *
     * @param uid 用户编号
     * @return 请求结果
     */
    ApiResult getOne(Integer uid);

    /**
     * 查询购票记录
     *
     * @param uid 购买人编号
     * @param pageNum  页码
     * @param pageSize 页容量
     * @return 请求结果
     */
    ApiResult getBuyHistory(Integer uid, Integer pageNum, Integer pageSize);
}
