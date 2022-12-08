package com.softesis.currency.repository

import com.softesis.currency.model.Rate
import org.springframework.data.repository.CrudRepository

interface RateRepository : CrudRepository<Rate, String> {}