package com.ajouin.ocrservice.dto

import java.util.UUID

data class OcrResponse(
    val id: UUID,
    val content: List<String>,
)
