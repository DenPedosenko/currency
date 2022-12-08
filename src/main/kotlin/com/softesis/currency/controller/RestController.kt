package com.softesis.currency.controller

import com.softesis.currency.model.Currency
import com.softesis.currency.model.Rate
import com.softesis.currency.service.CurrencyService
import com.softesis.currency.service.RateService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class RestController(val currencyService: CurrencyService, val rateService: RateService) {

    @PostMapping("/currencies")
    fun postCurrency(@RequestBody currency: Currency): ResponseEntity<Currency> {
        val location: URI = URI.create(String.format("/currencies/%s", currencyService.create(currency).code))
        return ResponseEntity.created(location).build()
    }

    @PostMapping("/rates")
    fun postRate(): ResponseEntity<Rate> {
        val currencies = currencyService.findAll()
        currencies.forEach {
            rateService.getExchangeRatesAndCreate(it)
        }

        val location: URI = URI.create("/rates")
        return ResponseEntity.created(location).build()
    }

}