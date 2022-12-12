package com.softesis.currency.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ScheduledService(val currencyService: CurrencyService, val rateService: RateService) {
    @Scheduled(fixedDelay = 10000)
    fun getRates(){
        val currencies = currencyService.findAll()
        currencies.forEach {
            rateService.getExchangeRatesAndCreate(it, null)
        }
    }
}