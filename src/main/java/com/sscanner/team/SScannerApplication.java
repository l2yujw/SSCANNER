package com.sscanner.team;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling   // 스케줄링
@EnableJpaAuditing //등록시각, 수정시각을 위한 전체 auditing 활성화를 위한 에노테이션
@EnableCaching
@SpringBootApplication(exclude = {ElasticsearchDataAutoConfiguration.class})
public class SScannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SScannerApplication.class, args);
    }

}


