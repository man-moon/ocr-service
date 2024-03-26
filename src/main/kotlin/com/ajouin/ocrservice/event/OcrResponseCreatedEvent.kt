package com.ajouin.ocrservice.event

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class OcrResponseCreatedEvent @JsonCreator constructor (
    @JsonProperty("id") val id: Long,
    @JsonProperty("url") val url: List<String>,
)
