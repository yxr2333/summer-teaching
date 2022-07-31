package com.ssssheep.summer.service;

import com.ssssheep.summer.pojo.dto.ApiResult;
import com.ssssheep.summer.pojo.vo.BuyTicketParam;
import com.ssssheep.summer.pojo.vo.ReturnTicketParam;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.service
 * @datetime 2022/7/31 Sunday
 */

public interface MovieTicketService {

    /**
     * 购买电影票
     * @param param 参数
     * @return 购买结果
     */
    ApiResult buyTicket(BuyTicketParam param);

    /**
     * 退票
     * @param param 参数
     * @return 退票结果
     */
    ApiResult returnTicket(ReturnTicketParam param);
}
