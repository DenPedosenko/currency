package com.softesis.currency.controller

import com.softesis.currency.model.Currency
import com.softesis.currency.model.Rate
import com.softesis.currency.service.CurrencyService
import com.softesis.currency.service.RateService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.net.URI
import java.time.LocalDate

@RestController
class RestController(val currencyService: CurrencyService, val rateService: RateService) {

    @PostMapping("/currency")
    fun postCurrency(@RequestBody currency: Currency): ResponseEntity<String> {
        val location: URI = URI.create(String.format("/currency/%s", currencyService.create(currency).code))
        return ResponseEntity.created(location).build()
    }

    @PostMapping("/currencies")
    fun postAllCurrencies(@RequestBody currencies:Iterable<Currency>):ResponseEntity<String>{
        val location: URI = URI.create("/currencies")
        currencyService.createAll(currencies)
        return ResponseEntity.created(location).build()
    }

    @PostMapping("/rates")
    fun postRate(@RequestParam(required = false) date: String?): ResponseEntity<String> {
        val currencies = currencyService.findAll()
        currencies.forEach {
            rateService.getExchangeRatesAndCreate(it, date)
        }

        val location: URI = URI.create("/rates?")
        return ResponseEntity.created(location).build()
    }

    @GetMapping("/rates")
    fun getAllRates():MutableIterable<Rate> = rateService.findAll()

    @GetMapping("/rates/date={date}")
    fun getAllRatesByDate(@PathVariable date:String):MutableIterable<Rate> {
        val localDate: LocalDate
        try {
            localDate = LocalDate.parse(date)
        } catch (ex: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong date format", ex)
        }

        return rateService.findAllByDate(localDate)
    }

    @GetMapping("/rates/code={code}")
    fun getAllRatesByCurrency(@PathVariable code:String):MutableIterable<Rate> {
        return rateService.findAllByCurrency(code)
    }

    @GetMapping("/rates/code={code}/date={date}")
    fun getAllRatesByCurrencyAndDate(@PathVariable code:String, @PathVariable date: String):MutableIterable<Rate> {
        val localDate: LocalDate
        try {
            localDate = LocalDate.parse(date)
        } catch (ex: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong date format", ex)
        }

        return rateService.findAllByCurrencyAndDate(code, localDate)
    }
}