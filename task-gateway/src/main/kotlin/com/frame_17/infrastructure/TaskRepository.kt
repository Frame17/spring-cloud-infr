package com.frame_17.infrastructure

import com.frame_17.model.Task
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference

class TaskRepository @Autowired constructor(
        private val rabbitTemplate: RabbitTemplate
) {

    fun fetch(): Task = rabbitTemplate.receiveAndConvert(object : ParameterizedTypeReference<Task>() {})

    fun save(task: Task) = rabbitTemplate.convertAndSend(task)
}