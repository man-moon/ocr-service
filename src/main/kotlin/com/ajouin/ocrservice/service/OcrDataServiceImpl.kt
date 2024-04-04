package com.ajouin.ocrservice.service

import com.ajouin.ocrservice.dto.OcrRequest
import com.ajouin.ocrservice.dto.OcrResponse
import com.ajouin.ocrservice.event.OcrResponseCreatedEvent
import com.ajouin.ocrservice.logger
import com.ajouin.ocrservice.publisher.OcrResponseEventPublisher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.cloud.gcp.vision.CloudVisionException
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

        val results: MutableList<String> = mutableListOf()

        request.imageUrl.forEach { url ->
            try {
                val text = cloudVisionTemplate.extractTextFromImage(resourceLoader.getResource(url))
                results.add(text)
            } catch (e: CloudVisionException) {
                logger.error { "OCR 에러 발생: ${e.message}, 해당 이미지는 건너뛰고 다음 이미지 진행" }
            }
        }

        return OcrResponse(request.id, results)
    }
}