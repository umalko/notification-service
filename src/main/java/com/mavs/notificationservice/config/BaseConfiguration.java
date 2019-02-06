package com.mavs.notificationservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAsync
@EnableEurekaClient
@EnableTransactionManagement

@ComponentScan({"com.mavs"})
@EntityScan("com.mavs")
@EnableMongoRepositories("com.mavs")
public class BaseConfiguration {
}
