package com.ssssheep.summer;

import cn.hutool.core.util.RandomUtil;
import org.junit.Test;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer
 * @datetime 2022/7/31 Sunday
 */
public class CommonTest {
    @Test
    public void test() {
        for(int i = 0;i < 10;i++){
            System.out.println(RandomUtil.randomStringUpper(5));
        }
    }
}
