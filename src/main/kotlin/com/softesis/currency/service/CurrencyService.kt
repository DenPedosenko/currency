package com.softesis.currency.service

import com.softesis.currency.model.Currency
import com.softesis.currency.repository.CurrencyRepository
import org.springframework.stereotype.Service


@Service
class CurrencyService( val db:CurrencyRepository) {
    fun create(currency: Currency): Currency = db.save(currency)
    fun createAll(currencies: List<Currency>): List<Currency> = db.saveAll(currencies).toList()
    fun findAll() : Set<Currency> = db.findAll().toSet()
}
