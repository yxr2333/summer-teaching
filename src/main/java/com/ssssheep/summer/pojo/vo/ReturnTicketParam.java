package com.ssssheep.summer.pojo.vo;

import lombok.Data;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.pojo.vo
 * @datetime 2022/7/31 Sunday
 */
@Data
public class ReturnTicketParam {

    private Integer orderId;
    private Integer returnerId;
    private Integer movieSessionId;
}
