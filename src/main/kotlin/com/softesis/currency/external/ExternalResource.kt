package com.softesis.currency.external

interface ExternalResource<T, R> {
    fun getData(param:T): R
}