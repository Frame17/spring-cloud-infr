package com.frame_17.configuration

import com.frame_17.configuration.RabbitProperties.Companion.PREFIX
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = PREFIX)
class RabbitProperties {
    companion object {
        const val PREFIX = "com.frame17.rabbit"
    }

    var queueName: String = "main"
    var exchangeName: String = "main"
}