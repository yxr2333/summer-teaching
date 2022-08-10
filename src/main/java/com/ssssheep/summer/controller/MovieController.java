package com.ssssheep.summer.controller;

import com.ssssheep.summer.pojo.dto.ApiResult;
import com.ssssheep.summer.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.controller
 * @datetime 2022/7/29 星期五
 */
@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    final MovieService movieService;

    @GetMapping("/all")
    public ApiResult getAllMovies(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize) {
        return movieService.getAllMovies(pageNum, pageSize);
    }

    @GetMapping("/{uid}")
    public ApiResult getOne(@PathVariable Integer uid) {
        if(uid == null) {
            return ApiResult.error("请输入电影编号");
        }
        return movieService.getOne(uid);
    }

    @GetMapping("/session")
    public ApiResult getMovieSessions(@RequestParam Integer movieId) {
        return movieService.getSessionsByMovieId(movieId);
    }
}
