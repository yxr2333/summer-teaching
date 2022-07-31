package com.ssssheep.summer.controller;

import com.ssssheep.summer.pojo.dto.ApiResult;
import com.ssssheep.summer.pojo.vo.BuyTicketParam;
import com.ssssheep.summer.pojo.vo.ReturnTicketParam;
import com.ssssheep.summer.service.MovieTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.controller
 * @datetime 2022/7/31 Sunday
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/business")
public class MovieTicketController {

    final MovieTicketService movieTicketService;

    @PostMapping("/buy")
    public ApiResult buyTicket(@RequestBody BuyTicketParam param) {
        return movieTicketService.buyTicket(param);
    }

    @PostMapping("/ret")
    public ApiResult returnTicket(@RequestBody ReturnTicketParam param) {
        return movieTicketService.returnTicket(param);
    }
}
