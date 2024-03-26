package com.ajouin.ocrservice.config

import com.google.api.gax.core.FixedCredentialsProvider
import com.google.auth.oauth2.ServiceAccountCredentials
import com.google.cloud.vision.v1.ImageAnnotatorClient
import com.google.cloud.vision.v1.ImageAnnotatorSettings
import org.springframework.cloud.gcp.vision.CloudVisionTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream

@Configuration
class CloudVisionTemplateConfig {
    @Bean
    fun getCloudVisionTemplate(): CloudVisionTemplate {
        val credentialsStream = FileInputStream("gcpvision.json")
        val credentials = ServiceAccountCredentials.fromStream(credentialsStream)
        val settings = ImageAnnotatorSettings.newBuilder()
            .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
            .build()
        val client = ImageAnnotatorClient.create(settings)
        return CloudVisionTemplate(client)
    }
}