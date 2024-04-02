package com.ajouin.ocrservice.service

import com.ajouin.ocrservice.dto.OcrRequest
import com.ajouin.ocrservice.dto.OcrResponse
import com.ajouin.ocrservice.event.OcrResponseCreatedEvent
import com.ajouin.ocrservice.publisher.OcrResponseEventPublisher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.cloud.gcp.vision.CloudVisionTemplate
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service

@Service
class OcrDataServiceImpl(
    private val cloudVisionTemplate: CloudVisionTemplate,
    private val resourceLoader: ResourceLoader,
    private val ocrResponseEventPublisher: OcrResponseEventPublisher,
): OcrDataService {

    override fun processOcrRequest(request: OcrRequest) {

        val result = extractText(request)

        ocrResponseEventPublisher.publish(
            OcrResponseCreatedEvent(result.id, result.content)
        )
    }

    override fun extractText(request: OcrRequest): OcrResponse {
        val results: List<String> = request.imageUrl.map { url ->
            cloudVisionTemplate.extractTextFromImage(resourceLoader.getResource(url))
        }

        return OcrResponse(request.id, results)
    }
}