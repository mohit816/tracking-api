package com.example.demo.service;

import com.example.demo.dto.TrackingNumberResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static java.lang.Boolean.TRUE;

@Service
public class TrackingNumberService {

    private static final Logger logger = LoggerFactory.getLogger(TrackingNumberService.class);
//    private final RedisTemplate<String, String> redisTemplate;

//    public TrackingNumberService(RedisTemplate<String, String> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }

    public TrackingNumberResponse generate(String origin, String destination, BigDecimal weight,
                                           OffsetDateTime createdAt, UUID customerId,
                                           String customerName, String customerSlug) {
        final int MAX_ATTEMPTS = 5;
        String trackingNumber = null;

        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            String seed = (origin + destination + customerSlug +
                    customerId + createdAt.toInstant().toEpochMilli() +
                    System.nanoTime()).toUpperCase();

            // Use MD5 and limit to 16 chars
            String hash = DigestUtils.md5DigestAsHex(seed.getBytes());
            trackingNumber = hash.substring(0, 16).toUpperCase();

            // Try to store in Redis (ensures uniqueness)
//            boolean unique = Boolean.TRUE.equals(redisTemplate.opsForValue()
//                    .setIfAbsent("tracking:" + trackingNumber, "1"));
            boolean unique=TRUE;

            if (unique) {
                logger.info("Tracking number generated: {}", trackingNumber);
                return new TrackingNumberResponse(trackingNumber, OffsetDateTime.now());
            }

            logger.warn("Tracking number collision. Retrying... attempt={}", attempt + 1);
        }

        // All attempts failed
        throw new RuntimeException("Unable to generate a unique tracking number after multiple attempts.");
    }
}
