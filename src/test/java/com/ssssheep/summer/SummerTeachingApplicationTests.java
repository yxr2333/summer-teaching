package com.ssssheep.summer;

import cn.hutool.extra.mail.MailUtil;
import com.ssssheep.summer.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SummerTeachingApplicationTests {

	@Autowired
	private MovieService movieService;
	@Test
	void contextLoads() {
		MailUtil.send("54535282@qq.com", "测试", "邮件来自Java", false);
	}

}
