package com.softesis.currency.service

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service

import org.springframework.web.client.RestTemplate


@Service
class CurrencyService(restTemplateBuilder: RestTemplateBuilder) {
    private val restTemplate: RestTemplate

    init {
        restTemplate = restTemplateBuilder.build()
    }

    fun getCurrency(code:String):String? {
            val url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode={1}&json"
            return restTemplate.getForObject(url, String::class.java, code)
        }
}
