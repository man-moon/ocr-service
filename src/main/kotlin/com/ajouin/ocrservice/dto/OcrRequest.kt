package com.ajouin.ocrservice.dto

import java.util.UUID

data class OcrRequest(
    val id: UUID,
    val imageUrl: List<String>,
)
