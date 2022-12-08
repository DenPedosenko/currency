package com.softesis.currency.external

import com.softesis.currency.model.ExchangeRateResponse
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class NBUApi(restTemplateBuilder: RestTemplateBuilder): ExternalResource<String, Array<ExchangeRateResponse>?> {
    private val restTemplate: RestTemplate
    init {
        restTemplate = restTemplateBuilder.build()
    }
    override fun getData(param: String): Array<ExchangeRateResponse>? {
        val url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode={1}&json"
        return restTemplate.getForObject(url, Array<ExchangeRateResponse>::class.java, param)
    }
}