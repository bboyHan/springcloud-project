package com.bboyhan.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/25 18:57
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableElasticsearchRepositories
public class SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }
}
