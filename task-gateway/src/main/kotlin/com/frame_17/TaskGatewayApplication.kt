package com.frame_17

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
class TaskGatewayApplication

fun main(args: Array<String>) {
    runApplication<TaskGatewayApplication>(*args)
}