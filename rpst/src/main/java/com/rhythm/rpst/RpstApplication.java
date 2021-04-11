package com.rhythm.rpst;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableEurekaClient
@SpringBootApplication
@EnableRedisHttpSession
@MapperScan("com.rhythm.rpst.mapper")
public class RpstApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpstApplication.class, args);
    }

}
