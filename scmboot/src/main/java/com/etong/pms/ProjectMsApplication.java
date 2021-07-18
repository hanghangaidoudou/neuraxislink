package com.etong.pms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// 启用定时任务
@EnableScheduling
public class ProjectMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectMsApplication.class, args);
    }
}
