package com.softesis.currency.model

import java.io.Serializable
import javax.persistence.*

@Entity
data class Currency(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,
    val code: String,
    val text: String,
) : Serializable