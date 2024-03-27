package com.ajouin.ocrservice.service

import com.ajouin.ocrservice.dto.OcrRequest
import com.ajouin.ocrservice.dto.OcrResponse

interface OcrDataService {

    suspend fun processOcrRequest(request: OcrRequest)
    suspend fun extractText(request: OcrRequest): OcrResponse
}