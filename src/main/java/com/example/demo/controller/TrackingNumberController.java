package com.example.demo.controller;

import com.example.demo.dto.TrackingNumberResponse;
import com.example.demo.service.TrackingNumberService;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.slf4j.Logger;

@RestController
@RequestMapping("/api")
public class TrackingNumberController {

    private final TrackingNumberService trackingNumberService;

    private static final Logger logger = LoggerFactory.getLogger(TrackingNumberController.class);


    public TrackingNumberController(TrackingNumberService trackingNumberService) {
        this.trackingNumberService = trackingNumberService;
    }

    @GetMapping("/next-tracking-number")
    public ResponseEntity<TrackingNumberResponse> getNextTrackingNumber(
            @RequestParam String origin_country_id,
            @RequestParam String destination_country_id,
            @RequestParam BigDecimal weight,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime created_at,
            @RequestParam UUID customer_id,
            @RequestParam String customer_name,
            @RequestParam String customer_slug) {

        logger.info("🔄 Received request to generate tracking number");
        logger.debug("Origin: {}, Destination: {}, Weight: {}, Customer: {} ({}), CreatedAt: {}, Slug: {}",
                origin_country_id, destination_country_id, weight, customer_name, customer_id, created_at, customer_slug);

        TrackingNumberResponse response = trackingNumberService.generate(
                origin_country_id, destination_country_id, weight,
                created_at, customer_id, customer_name, customer_slug
        );

        logger.info("✅ Tracking number generated successfully: {}", response.getTracking_number());

        return ResponseEntity.ok(response);
    }
}
