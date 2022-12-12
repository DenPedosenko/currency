package com.softesis.currency.controller

import com.softesis.currency.model.Currency
import com.softesis.currency.model.Rate
import com.softesis.currency.service.CurrencyService
import com.softesis.currency.service.RateService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

@RestController
class RestController(val currencyService: CurrencyService, val rateService: RateService) {
    @PostMapping("/currency")
    fun postCurrency(@RequestBody currency: Currency) {
        currencyService.create(currency)
    }

    @PostMapping("/currencies")
    fun postAllCurrencies(@RequestBody currencies:List<Currency>){
        currencyService.createAll(currencies)
    }

    @PostMapping("/rates")
    fun postRate(@RequestParam(required = false) date: String?) {
        val currencies = currencyService.findAll()
        currencies.forEach {
            rateService.getExchangeRatesAndCreate(it, date)
        }
    }

    @GetMapping("/rates")
    fun getAllRates():MutableIterable<Rate> = rateService.findAll()

    @GetMapping("/rates/date={date}")
    fun getAllRatesByDate(@PathVariable date:String): List<Rate> {
        val localDate: LocalDate
        try {
            localDate = LocalDate.parse(date)
        } catch (ex: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong date format", ex)
        }
        return rateService.findAllByDate(localDate)
    }

    @GetMapping("/rates/code={code}")
    fun getAllRatesByCurrency(@PathVariable code:String):List<Rate> {
        return rateService.findAllByCurrency(code)
    }

    @GetMapping("/rates/code={code}/date={date}")
    fun getAllRatesByCurrencyAndDate(@PathVariable code:String, @PathVariable date: String):List<Rate> {
        val localDate: LocalDate
        try {
            localDate = LocalDate.parse(date)
        } catch (ex: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong date format", ex)
        }
        return rateService.findAllByCurrencyAndDate(code, localDate)
    }
}