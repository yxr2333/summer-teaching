package com.ssssheep.summer.util;

import com.ssssheep.summer.dao.MovieDao;
import com.ssssheep.summer.dao.UserDao;
import com.ssssheep.summer.pojo.dto.ApiResult;
import com.ssssheep.summer.pojo.dto.PageData;
import com.ssssheep.summer.pojo.entity.Movie;
import com.ssssheep.summer.pojo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.util
 * @datetime 2022/7/31 Sunday
 */
@Component
@RequiredArgsConstructor
public class Utils<T> {

    @PersistenceContext
    final EntityManager entityManager;

    final UserDao userDao;
    final MovieDao movieDao;

    public ApiResult makePageData(Integer pageNum, Integer pageSize, Class<T> clazz) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageRequest pageable = PageRequest.of(pageNum - 1, pageSize);
        if (clazz.equals(Movie.class)) {
            Page<Movie> moviePage = movieDao.findAll(pageable);
            return ApiResult.success(new PageData<Movie>().build(
                            moviePage.getTotalPages(),
                            moviePage.getTotalElements(),
                            moviePage.getContent()));
        } else if (clazz.equals(User.class)) {
            Page<User> userPage = userDao.findAll(pageable);
            return ApiResult.success(new PageData<User>().build(
                    userPage.getTotalPages(),
                    userPage.getTotalElements(),
                    userPage.getContent()));
        } else {
            throw new RuntimeException("不支持的分页类型");
        }
    }
}
