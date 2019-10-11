package com.example.billspliter.utilities

import RestTemplateLoggingInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestOperations

@Configuration
class RestOperationsConfig {
    @Bean
    fun restOperations(): RestOperations {
        return RestTemplateBuilder().additionalInterceptors(RestTemplateLoggingInterceptor()).build()
    }
}