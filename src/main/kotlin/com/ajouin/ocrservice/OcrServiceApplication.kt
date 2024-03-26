package com.ajouin.ocrservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class OcrServiceApplication

fun main(args: Array<String>) {
	runApplication<OcrServiceApplication>(*args)
}
