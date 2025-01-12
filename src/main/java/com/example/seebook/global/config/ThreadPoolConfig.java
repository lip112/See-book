package com.example.seebook.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@RequiredArgsConstructor
@EnableAsync
public class ThreadPoolConfig {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4); // 기본 쓰레드 수
        executor.setMaxPoolSize(8); // 최대 쓰레드 수
        executor.setQueueCapacity(10); // 작업 대기열 크기
        executor.setKeepAliveSeconds(60); // 쓰레드 유지 시간
        executor.setThreadNamePrefix("MyThread-"); // 쓰레드 이름
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize(); // 초기화
        return executor;
    }
}
