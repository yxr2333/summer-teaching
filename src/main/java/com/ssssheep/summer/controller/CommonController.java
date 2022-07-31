package com.ssssheep.summer.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.MailUtil;
import com.ssssheep.summer.dao.MovieDao;
import com.ssssheep.summer.pojo.dto.ApiResult;
import com.ssssheep.summer.pojo.dto.SlideShowData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.controller
 * @datetime 2022/7/31 Sunday
 */
@RestController
@RequestMapping("/common")
@RequiredArgsConstructor
public class CommonController {

    final MovieDao movieDao;

    private static final String FIND_PWD_MAIL_TITLE = "验证找回密码";


    @GetMapping("/slideshow")
    public ApiResult getSlideShowPics() {
        List<SlideShowData> list = new ArrayList<>();
        movieDao.randomAccessMovies().forEach(item -> {
            SlideShowData data = new SlideShowData();
            data.setImgUrl(item.getMovieCover());
            data.setMovieId(item.getId());
            data.setMovieName(item.getName());
            list.add(data);
        });
        return ApiResult.success(list);
    }

    @PostMapping("/sendCode")
    public ApiResult sendVerifyCode(HttpServletRequest request, @RequestParam String mail) {
        String verifyCode = RandomUtil.randomStringUpper(5);
        request.getSession().setAttribute("verifyCode", verifyCode);
        request.getSession().setAttribute("startTime", LocalDateTime.now());
        MailUtil.send(mail, FIND_PWD_MAIL_TITLE, "您正在找回密码,验证码为:" + verifyCode, false);
        return ApiResult.success("邮件发送成功");
    }
}
