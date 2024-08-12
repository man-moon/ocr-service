package com.ajouin.ocrservice

import com.ajouin.ocrservice.dto.OcrRequest
import com.ajouin.ocrservice.service.OcrDataService
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.*

@SpringBootTest
@ActiveProfiles("test")
class OcrDataServiceTest @Autowired constructor(
    private val ocrDataService: OcrDataService,
) {

    @Test
    @DisplayName("이미지의 내용이 텍스트로 반환")
    fun processOcrRequestTest() {

        val ocrRequest = OcrRequest(
            id = UUID.randomUUID(),
            imageUrl = listOf(
                "https://www.ajou.ac.kr/_attach/ajou/editor-image/2024/08/PwuJFWVfmuNydcrvowBxaQJiSX.jpg",
                "https://www.ajou.ac.kr/_attach/ajou/editor-image/2024/08/pYlVfLoUwOvuOsSyKCwPLjFpOH.png"
            )
        )

        val ocrResponse = ocrDataService.extractText(ocrRequest)
        assertThat(ocrResponse.content.size).isEqualTo(2)
        assertThat(ocrResponse.content[0]).isNotBlank()
        assertThat(ocrResponse.content[1]).isNotBlank()
    }
}