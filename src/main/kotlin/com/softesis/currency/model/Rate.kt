package com.softesis.currency.model

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "rate")
data class Rate(
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    val id: Int?,
    @Column(name = "date")
    val exchangeDate: LocalDate,

    val rate: Double,

    @ManyToOne
    @JoinColumn(name = "currency_id")
    val currency: Currency
) : Serializable