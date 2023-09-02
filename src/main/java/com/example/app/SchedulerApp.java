package com.example.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class SchedulerApp {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    @Value("${app.scheduler.cron}")
    private String cronValue;

    @Value("${app.scheduler.fixed-rate}")
    private String fixedRateString;

    @Value("${app.scheduler.initial-delay}")
    private String initialDelayString;

    @Value("${app.scheduler.fixed-delay}")
    private String fixedDelayString;

//    @Scheduled(cron = "${app.scheduler.cron}")
    public void scheduledMethod1(){
        log.info("Scheduled method with cron value: {}", cronValue);
        dateTimeLogger();
    }

//    @Scheduled(fixedRateString = "${app.scheduler.fixed-rate}")
    public void scheduledMethod2(){
        log.info("Scheduled method using fixedRateString: {}", fixedRateString);
        dateTimeLogger();
    }

//    @Scheduled(initialDelayString = "${app.scheduler.initial-delay}", fixedDelayString = "${app.scheduler.fixed-delay}")
    public void scheduledMethod3(){
        log.info("Scheduled method using initialDelayString: {}, fixedDelayString: {}",
                initialDelayString, fixedDelayString);
        dateTimeLogger();
    }

    private void dateTimeLogger(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS)
                .withZone(ZoneId.systemDefault());
        String currentDateTime = dateTimeFormatter.format(Instant.now());
        log.info("Current Date Time: {}", currentDateTime);
    }
}
