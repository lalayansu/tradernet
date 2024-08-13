package com.example.data.entity


data class QuoteEntity(
    val ticker: String? = null,
    val priceChangeInPoints: Double? = null,
    val latestTradePrice: Double? = null,
    val exchangeOfLatestTrade: String? = null,
    val minStep: Double? = null,
    val name: String? = null,
    val priceChangeByPercentage: Double? = null,
)