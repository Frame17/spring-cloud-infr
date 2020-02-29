package com.frame_17.configuration

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(RabbitProperties::class)
class RabbitConfiguration @Autowired constructor(
        private val properties: RabbitProperties
) {

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val connectionFactory = CachingConnectionFactory("localhost")
        connectionFactory.username = "guest"
        connectionFactory.setPassword("guest")
        return connectionFactory
    }

    @Bean
    fun mainQueue(): Queue {
        return Queue(properties.queueName, true)
    }

    @Bean
    @ConditionalOnProperty(name = ["com.frame17.rabbit.secondary"], havingValue = "enable", matchIfMissing = false)
    fun secondaryQueue(): Queue {
        return Queue("secondary", false)
    }

    @Bean
    fun rabbitTemplate(): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory())
        rabbitTemplate.setDefaultReceiveQueue(properties.queueName)
        rabbitTemplate.messageConverter = messageConverter()
        rabbitTemplate.exchange = properties.exchangeName
        return rabbitTemplate
    }

    @Bean
    fun messageConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }
}