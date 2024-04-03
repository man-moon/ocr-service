package com.ajouin.ocrservice

import com.ajouin.ocrservice.dto.OcrRequest
import com.ajouin.ocrservice.service.OcrDataService
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class OcrDataServiceTest @Autowired constructor(
    private val ocrDataService: OcrDataService,
) {

    @Test
    @DisplayName("이미지의 내용이 텍스트로 반환")
    fun processOcrRequestTest() {

        val ocrRequest = OcrRequest(
            id = UUID.randomUUID(),
            imageUrl = listOf(
                "https://ajouin-notice-content.s3.ap-northeast-2.amazonaws.com/fcc347e6-154f-4791-acfc-f53f4e7c3666",
                "https://ajouin-notice-content.s3.ap-northeast-2.amazonaws.com/f812b1be-60a3-48b3-8450-effa98930ffe"
            )
        )

        val ocrResponse = ocrDataService.extractText(ocrRequest)
        assertThat(ocrResponse.content.size).isEqualTo(2)
        assertThat(ocrResponse.content[0]).isNotBlank()
        assertThat(ocrResponse.content[1]).isNotBlank()
    }
}