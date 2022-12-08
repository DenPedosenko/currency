package com.softesis.currency.service

import com.softesis.currency.model.Currency
import com.softesis.currency.repository.CurrencyRepository
import org.springframework.stereotype.Service


@Service
class CurrencyService( val db:CurrencyRepository) {
    fun create(currency: Currency): Currency {
        return db.save(currency)
    }

    fun findAll() : MutableIterable<Currency> = db.findAll()
}
