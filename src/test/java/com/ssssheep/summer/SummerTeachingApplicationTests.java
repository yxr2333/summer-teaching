package com.ssssheep.summer;

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
		System.out.println(movieService.getSessionsByMovieId(7051806));
	}

}
