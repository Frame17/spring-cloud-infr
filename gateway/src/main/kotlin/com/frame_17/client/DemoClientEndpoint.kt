package com.frame_17.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoClientEndpoint @Autowired constructor(
        private val demoClient: DemoClient
) {
    @GetMapping("demo")
    fun demo() = demoClient.endpoint()
}