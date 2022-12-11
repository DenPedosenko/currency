package com.softesis.currency.repository

import com.softesis.currency.model.Currency
import org.springframework.data.repository.CrudRepository

interface CurrencyRepository : CrudRepository<Currency, String>{}

