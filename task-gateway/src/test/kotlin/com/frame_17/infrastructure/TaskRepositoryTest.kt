package com.frame_17.infrastructure

import com.frame_17.model.Task
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.testcontainers.containers.GenericContainer

@RunWith(SpringRunner::class)
@SpringBootTest
class TaskRepositoryTest {

    @Autowired
    lateinit var taskRepository: TaskRepository
    @Autowired
    lateinit var rabbitTemplate: RabbitTemplate
    @Autowired
    lateinit var rabbitAdmin: RabbitAdmin
    @Autowired
    lateinit var queue: Queue

    companion object {
        @get:ClassRule
        val rabbit: GenericContainer<*> = GenericContainer<Nothing>("rabbitmq:3.7.8-alpine")
                .withExposedPorts(5672)

        private val TASK = Task("kk")
    }

    @After
    fun tearDown() {
        rabbitAdmin.purgeQueue(queue.name)
    }

    @Test
    fun fetch() {
        rabbitTemplate.convertAndSend(TASK)

        taskRepository.fetch().apply {
            assertEquals(this, TASK)
        }
    }

    @Test
    fun save() {
        taskRepository.save(TASK)

        rabbitTemplate.receiveAndConvert().apply {
            assertEquals(this, TASK)
        }
    }
}