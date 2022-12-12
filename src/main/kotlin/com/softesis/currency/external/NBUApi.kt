package com.softesis.currency.external

import com.softesis.currency.model.ExchangeRateResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class NBUApi(restTemplateBuilder: RestTemplateBuilder) {
    @Value("\${external.api}")
    lateinit var  DATA_URI_TEMPLATE:String

    @Value("\${external.api.byDate}")
    lateinit var DATA_URI_TEMPLATE_BY_DATE:String

    private val restTemplate: RestTemplate
    init {
        restTemplate = restTemplateBuilder.build()
    }
    fun getData(param: String): Array<ExchangeRateResponse>? {
        val url = String.format(DATA_URI_TEMPLATE, param)
        return restTemplate.getForObject(url, Array<ExchangeRateResponse>::class.java, param)
    }

    fun getDataOnDate(param: String, date: String): Array<ExchangeRateResponse>? {
        val url = String.format(DATA_URI_TEMPLATE_BY_DATE, param, date)
        return restTemplate.getForObject(url, Array<ExchangeRateResponse>::class.java, param, date)
    }
}