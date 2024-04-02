package com.ajouin.ocrservice.listener

import com.ajouin.ocrservice.service.OcrDataService
import com.ajouin.ocrservice.dto.OcrRequest
import com.ajouin.ocrservice.logger
import com.fasterxml.jackson.databind.ObjectMapper
import io.awspring.cloud.sqs.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
class OcrRequestEventListener(
    private val ocrDataService: OcrDataService,
    private val objectMapper: ObjectMapper,
) {

    // ocr_request_queue 지연시간 10초 설정됨(Storage에 이미지가 저장되는 시간 고려)
    @SqsListener("\${events.queues.ocr-request-queue}")
    fun receiveContentRequest(message: String) {
        logger.info { "Received message: $message" }
        val request: OcrRequest = objectMapper.readValue(message, OcrRequest::class.java)

        ocrDataService.processOcrRequest(request)
    }
}