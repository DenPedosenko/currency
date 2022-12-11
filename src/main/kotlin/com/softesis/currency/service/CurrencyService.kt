package com.softesis.currency.service

import com.softesis.currency.model.Currency
import com.softesis.currency.repository.CurrencyRepository
import org.springframework.stereotype.Service


@Service
class CurrencyService( val db:CurrencyRepository) {
    fun create(currency: Currency): Currency {
        return db.save(currency)
    }

    fun createAll(currencies: Iterable<Currency>) = db.saveAll(currencies)
    fun findAll() : MutableSet<Currency> = db.findAll().toMutableSet()
}
