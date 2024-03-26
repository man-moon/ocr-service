package com.ajouin.ocrservice

import com.ajouin.ocrservice.dto.OcrRequest
import com.ajouin.ocrservice.dto.OcrResponse

interface OcrDataProcessor {

    suspend fun processOcrRequest(request: OcrRequest)
    suspend fun extractText(request: OcrRequest): OcrResponse
}