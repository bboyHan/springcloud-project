package com.bboyhan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/24 18:15
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ConsumerUserFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerUserFeignApplication.class, args);
    }
}
