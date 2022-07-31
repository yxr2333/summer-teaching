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
public class CreateUserParam {
    private String username;
    private String password;
    private String mail;
}
