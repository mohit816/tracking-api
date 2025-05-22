package com.example.demo;

import com.example.demo.service.TrackingNumberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TrackingApiApplicationTests {
	@Autowired
	private TrackingNumberService trackingNumberService;
	@Test
	void contextLoads() {
		assertNotNull(trackingNumberService);
	}

}
