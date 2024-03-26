package com.ajouin.ocrservice.publisher

import com.ajouin.ocrservice.config.EventQueuesProperties
import com.ajouin.ocrservice.event.OcrResponseCreatedEvent
import com.ajouin.ocrservice.logger
import com.fasterxml.jackson.databind.ObjectMapper
import io.awspring.cloud.sqs.operations.SqsTemplate
import org.springframework.stereotype.Component

@Component
class OcrResponseEventPublisher(
    private val objectMapper: ObjectMapper,
    private val sqsTemplate: SqsTemplate,
    private val eventQueuesProperties: EventQueuesProperties,
) {

    fun publish(event: OcrResponseCreatedEvent) {

        val messagePayload = objectMapper.writeValueAsString(event)

        sqsTemplate.send { to ->
            to.queue(eventQueuesProperties.ocrResponseQueue)
                .payload(messagePayload)
        }

        logger.info { "Message sent with payload $event" }
    }
}