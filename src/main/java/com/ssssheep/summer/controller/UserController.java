package com.ssssheep.summer.controller;

import com.ssssheep.summer.pojo.dto.ApiResult;
import com.ssssheep.summer.pojo.entity.User;
import com.ssssheep.summer.pojo.vo.CreateUserParam;
import com.ssssheep.summer.pojo.vo.ResetPwdParam;
import com.ssssheep.summer.pojo.vo.UserLoginParam;
import com.ssssheep.summer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.controller
 * @datetime 2022/7/31 Sunday
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    final UserService userService;

    @PostMapping("/login")
    public ApiResult doLogin(@RequestBody(required = false) UserLoginParam userLoginParam) {
        return userService.doLogin(userLoginParam);
    }

    @DeleteMapping("/{uid}")
    public ApiResult deleteOne(@PathVariable Integer uid) {
        if (uid == null) {
            return ApiResult.error("请输入uid");
        }
        return userService.deleteOne(uid);
    }

    @GetMapping("/all")
    public ApiResult getAll(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize) {
        return userService.getAll(pageNum, pageSize);
    }

    @PostMapping("")
    public ApiResult addOne(@RequestBody CreateUserParam param) {
        return userService.addOne(param);
    }

    @PutMapping("")
    public ApiResult updateOne(@RequestBody User user) {
        return userService.updateOne(user);
    }

    @GetMapping("/{uid}")
    public ApiResult getOne(@PathVariable Integer uid) {
        return userService.getOne(uid);
    }

    @GetMapping("/buy/history")
    public ApiResult getHistory(
            @RequestParam Integer uid,
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize) {
        return userService.getBuyHistory(uid, pageNum, pageSize);
    }

    @PostMapping("/reset/pwd")
    public ApiResult resetPwd(HttpServletRequest request, @RequestBody ResetPwdParam param) {
        return userService.findAndResetPwd(request, param);
    }

}
