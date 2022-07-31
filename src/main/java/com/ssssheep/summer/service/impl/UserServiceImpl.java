package com.ssssheep.summer.service.impl;

import com.ssssheep.summer.dao.MovieTicketDao;
import com.ssssheep.summer.dao.UserDao;
import com.ssssheep.summer.pojo.dto.ApiResult;
import com.ssssheep.summer.pojo.dto.PageData;
import com.ssssheep.summer.pojo.entity.MovieTicket;
import com.ssssheep.summer.pojo.entity.User;
import com.ssssheep.summer.pojo.vo.CreateUserParam;
import com.ssssheep.summer.pojo.vo.UserLoginParam;
import com.ssssheep.summer.service.UserService;
import com.ssssheep.summer.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.service.impl
 * @datetime 2022/7/31 Sunday
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final UserDao userDao;
    final MovieTicketDao movieTicketDao;
    final Utils<User> utils;

    /**
     * 用户登录
     *
     * @param param 登录参数
     * @return 请求结果
     */
    @Override
    public ApiResult doLogin(UserLoginParam param) {
        User user = Optional
                .ofNullable(userDao.getUserByName(param.getUsername()))
                .orElseThrow(() -> new RuntimeException("不存在此用户"));
        if (user.getPassword().equalsIgnoreCase(param.getPassword())) {
            return ApiResult.success("登录成功", user);
        } else {
            throw new RuntimeException("用户名或密码输入错误");
        }
    }

    /**
     * 添加用户/用户注册
     *
     * @param param 参数
     * @return 请求结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResult addOne(CreateUserParam param) {
        if (userDao.existsByName(param.getUsername())) {
            throw new RuntimeException("用户名已被使用,请重新输入");
        }
        User user = new User();
        user.setName(param.getUsername());
        user.setPassword(param.getPassword());
        user.setMail(param.getMail());
        return ApiResult.success("注册成功", userDao.save(user));
    }

    /**
     * 删除用户
     *
     * @param uid 用户编号
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResult deleteOne(Integer uid) {
        try {
            userDao.deleteById(uid);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("无效的用户编号");
        }
        return ApiResult.success("删除成功");
    }

    /**
     * 更新用户信息
     *
     * @param user 更新后的用户信息
     * @return 请求结果
     */
    @Override
    public ApiResult updateOne(User user) {
        if (!userDao.existsById(user.getUid())) {
            throw new RuntimeException("update: 无效的用户编号");
        }
        userDao.save(user);
        return ApiResult.success("更新成功");
    }

    /**
     * 查询所有用户的信息
     *
     * @param pageNum  页码
     * @param pageSize 页容量
     * @return 请求结果
     */
    @Override
    public ApiResult getAll(Integer pageNum, Integer pageSize) {
        return utils.makePageData(pageNum, pageSize, User.class);
    }

    /**
     * 查询某个用户的信息
     *
     * @param uid 用户编号
     * @return 请求结果
     */
    @Override
    public ApiResult getOne(Integer uid) {
        User user = userDao.findById(uid)
                .orElseThrow(() -> new RuntimeException("编号不存在"));
        return ApiResult.success("查询成功", user);
    }

    /**
     * 查询购票记录
     *
     * @param uid      购买人编号
     * @param pageNum  页码
     * @param pageSize 页容量
     * @return 请求结果
     */
    @Override
    public ApiResult getBuyHistory(Integer uid, Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if (!userDao.existsById(uid)) {
            throw new RuntimeException("不存在的用户编号");
        }
        PageRequest pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<MovieTicket> page = movieTicketDao.findAllByBuyerUidOrderByIdDesc(uid, pageable);
        return ApiResult.success(new PageData<MovieTicket>()
                .build(page.getTotalPages(), page.getTotalElements(), page.getContent()));
    }
}
