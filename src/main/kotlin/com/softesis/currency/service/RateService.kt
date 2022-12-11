package com.softesis.currency.service

import com.softesis.currency.external.NBUApi
import com.softesis.currency.model.Currency
import com.softesis.currency.model.ExchangeRateResponse
import com.softesis.currency.model.Rate
import com.softesis.currency.repository.RateRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class RateService(val api: NBUApi, val db: RateRepository) {

    fun create(rate: Rate) = db.save(rate)

    fun getExchangeRates(code: String): Array<ExchangeRateResponse>? = api.getData(code)

    fun getExchangeRatesOnDate(code: String, date: String) = api.getDataOnDate(code, date)

    fun getExchangeRatesAndCreate(currency: Currency, date: String?) {
        (if (date == null) getExchangeRates(currency.code)
        else getExchangeRatesOnDate(currency.code, date))?.let { rates ->
            rates.forEach { rate ->
                create(
                    Rate(
                        null,
                        LocalDate.parse(rate.exchangedate, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        rate.rate,
                        currency
                    )
                )
            }
        }
    }

    fun findAll(): MutableIterable<Rate> = db.findAll()

    fun findAllByDate(date: LocalDate): MutableIterable<Rate> = db.getAllByExchangeDate(date)

    fun findAllByCurrency(code: String): MutableIterable<Rate> = db.getAllByCurrencyCode(code)
    fun findAllByCurrencyAndDate(code: String, date: LocalDate): MutableIterable<Rate> =
        db.getAllByCurrencyCodeAndAndExchangeDate(code, date)
}
