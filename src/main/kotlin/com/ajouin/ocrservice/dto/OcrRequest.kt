package com.ajouin.ocrservice.dto

data class OcrRequest(
    val id: Long,
    val imageUrl: List<String>,
)
