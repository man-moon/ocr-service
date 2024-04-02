package com.ajouin.ocrservice.service

import com.ajouin.ocrservice.dto.OcrRequest
import com.ajouin.ocrservice.dto.OcrResponse

interface OcrDataService {

    fun processOcrRequest(request: OcrRequest)
    fun extractText(request: OcrRequest): OcrResponse
}