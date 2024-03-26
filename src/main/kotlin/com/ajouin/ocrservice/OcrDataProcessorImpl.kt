package com.ajouin.ocrservice

import com.ajouin.ocrservice.dto.OcrRequest
import com.ajouin.ocrservice.dto.OcrResponse
import com.ajouin.ocrservice.event.OcrResponseCreatedEvent
import com.ajouin.ocrservice.publisher.OcrResponseEventPublisher
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.cloud.vision.v1.ImageSource
import io.awspring.cloud.sqs.operations.SqsTemplate
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.cloud.gcp.vision.CloudVisionTemplate
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class OcrDataProcessorImpl(
    private val cloudVisionTemplate: CloudVisionTemplate,
    private val resourceLoader: ResourceLoader,
    private val ocrResponseEventPublisher: OcrResponseEventPublisher,
): OcrDataProcessor {

    override suspend fun processOcrRequest(request: OcrRequest) {

        val result = extractText(request)

        ocrResponseEventPublisher.publish(
            OcrResponseCreatedEvent(result.id, result.content)
        )
    }

    override suspend fun extractText(request: OcrRequest): OcrResponse = coroutineScope {
        val results: List<String> = request.imageUrl.map { url ->
            async {
                cloudVisionTemplate.extractTextFromImage(resourceLoader.getResource(url))
            }
        }.map { it.await() }

        OcrResponse(request.id, results)
    }
}