package com.ajouin.ocrservice.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class OcrRequest @JsonCreator constructor (
    @JsonProperty("id") val id: UUID,
    @JsonProperty("imageUrl") val imageUrl: List<String>,
)
