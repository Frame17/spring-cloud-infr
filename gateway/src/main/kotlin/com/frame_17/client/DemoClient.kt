package com.frame_17.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient("processor")
interface DemoClient {

    @GetMapping("demo")
    fun endpoint(): String
}