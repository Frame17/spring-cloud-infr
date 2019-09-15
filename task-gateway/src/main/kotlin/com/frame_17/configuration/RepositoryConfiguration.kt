package com.frame_17.configuration

import com.frame_17.infrastructure.TaskRepository
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RepositoryConfiguration @Autowired constructor(
        private val rabbitTemplate: RabbitTemplate
) {

    @Bean
    fun taskRepository() = TaskRepository(rabbitTemplate)
}