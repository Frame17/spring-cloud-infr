package com.frame_17.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.serviceregistry.Registration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoEndpoint {

    @Autowired
    lateinit var registration: Registration

    @GetMapping("demo")
    fun endpoint() = registration.instanceId
}