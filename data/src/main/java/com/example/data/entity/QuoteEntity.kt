package com.example.data.entity

import java.math.BigDecimal


data class QuoteEntity(
    val ticker: String? = null,
    val priceChangeInPoints: BigDecimal? = null,
    val latestTradePrice: BigDecimal? = null,
    val exchangeOfLatestTrade: String? = null,
    val minStep: BigDecimal? = null,
    val name: String? = null,
    val priceChangeByPercentage: BigDecimal? = null,
)