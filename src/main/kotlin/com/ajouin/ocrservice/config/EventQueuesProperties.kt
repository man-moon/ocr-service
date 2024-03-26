package com.ajouin.ocrservice.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "events.queues")
class EventQueuesProperties (
    val ocrRequestQueue: String,
    val ocrResponseQueue: String,
)