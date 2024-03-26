package com.ajouin.ocrservice

import com.ajouin.ocrservice.dto.OcrRequest
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class OcrDataProcessorTest @Autowired constructor(
    private val ocrDataProcessor: OcrDataProcessor,
) {

    @Test
    @DisplayName("이미지의 내용이 텍스트로 반환")
    fun processOcrRequestTest(): Unit = runBlocking {

        val ocrRequest = OcrRequest(
            id = 0L,
            imageUrl = listOf(
                "https://ajouin-notice-content.s3.ap-northeast-2.amazonaws.com/361fb049-f775-4d7b-8a3d-cdc1012865e3",
                "https://ajouin-notice-content.s3.ap-northeast-2.amazonaws.com/8dc87d89-757d-4630-8ba8-669c7c3ade84"
            )
        )

        val ocrResponse = ocrDataProcessor.extractText(ocrRequest)
        assertThat(ocrResponse.id).isEqualTo(0L)
        assertThat(ocrResponse.content.size).isEqualTo(2)
        assertThat(ocrResponse.content[0]).isNotBlank()
        assertThat(ocrResponse.content[1]).isNotBlank()
    }
}