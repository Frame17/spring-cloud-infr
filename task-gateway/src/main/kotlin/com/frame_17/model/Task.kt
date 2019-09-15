package com.frame_17.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class Task(
        @JsonProperty("name") val name: String
): Serializable