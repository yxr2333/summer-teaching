package com.ssssheep.summer.pojo.dto;

import java.util.HashMap;
import java.util.List;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.pojo.dto
 * @datetime 2022/7/31 Sunday
 */
public class PageData <T> {

    public HashMap<String,Object> build(int totalPages, long totalElements, List<T> content) {
        HashMap<String, Object> map = new HashMap<>(16);
        map.put("total_pages", totalPages);
        map.put("total_elements", totalElements);
        map.put("data", content);
        return map;
    }
}
