package com.softesis.currency.repository

import com.softesis.currency.model.Rate
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate

interface RateRepository : CrudRepository<Rate, String> {

    fun getAllByExchangeDate(exchangeDate: LocalDate):MutableIterable<Rate>
    fun getAllByCurrencyCode(code: String):MutableIterable<Rate>
    fun getAllByCurrencyCodeAndAndExchangeDate(code: String, exchangeDate: LocalDate):MutableIterable<Rate>


}