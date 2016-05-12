package com.abeosoft.microservices.news;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NewsMicroservice.class)
@WebAppConfiguration
public class NewsMicroserviceTests {

	@Test
	public void contextLoads() {
	}

}
