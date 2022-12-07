package com.softesis.currency.controller

import com.softesis.currency.service.CurrencyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController {

    @Autowired
    lateinit var service : CurrencyService

    @GetMapping
    fun index(@RequestParam("code") code: String) = service.getCurrency(code)
}