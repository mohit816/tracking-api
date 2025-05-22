package com.example.demo.service;

import com.example.demo.dto.TrackingNumberResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrackingNumberServiceTest {

    @Mock
    private RedisTemplate<String, String> redisTemplate;

    @Mock
    private ValueOperations<String, String> valueOperations;

    @InjectMocks
    private TrackingNumberService trackingNumberService;

    @BeforeEach
    void setUp() {
        // Properly mock RedisTemplate to return mocked ValueOperations
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    void generate_ShouldReturnValidTrackingNumber() {
        Logger logger = LoggerFactory.getLogger(TrackingNumberServiceTest.class);

        // Mock Redis setIfAbsent to simulate uniqueness success
        when(valueOperations.setIfAbsent(anyString(), eq("1"))).thenReturn(true);
        logger.info("🔍 Starting test: generate_ShouldReturnValidTrackingNumber");


        TrackingNumberResponse response = trackingNumberService.generate(
                "MY", "ID", new BigDecimal("1.234"),
                OffsetDateTime.now(), UUID.randomUUID(),
                "RedBox Logistics", "redbox-logistics");

        logger.debug("✅ Generated tracking number: {}", response.getTracking_number());

        assertNotNull(response.getTracking_number());
        assertEquals(16, response.getTracking_number().length());

        logger.info("✅ Test passed: Tracking number is valid and of correct length.");
    }
}

//package com.example.demo.service;
//
//import com.example.demo.dto.TrackingNumberResponse;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.math.BigDecimal;
//import java.time.OffsetDateTime;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TrackingNumberServiceTest {
//
//    private final TrackingNumberService trackingNumberService = new TrackingNumberService();
//
//    private static final Logger logger = LoggerFactory.getLogger(TrackingNumberServiceTest.class);
//
//    @Test
//    void generate_ShouldReturnValidTrackingNumber() {
//        logger.info("🔍 Starting test: generate_ShouldReturnValidTrackingNumber");
//
//        TrackingNumberResponse response = trackingNumberService.generate(
//                "MY", "ID", new BigDecimal("1.234"),
//                OffsetDateTime.now(), UUID.randomUUID(),
//                "RedBox Logistics", "redbox-logistics");
//
//        logger.debug("✅ Generated tracking number: {}", response.getTracking_number());
//
//        assertNotNull(response.getTracking_number());
//        assertEquals(16, response.getTracking_number().length());
//
//        logger.info("✅ Test passed: Tracking number is valid and of correct length.");
//    }
//}

