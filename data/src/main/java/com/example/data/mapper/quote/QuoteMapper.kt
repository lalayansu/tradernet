package com.example.data.mapper.quote

import com.example.data.entity.QuoteEntity
import com.example.data.mapper.Mapper
import com.example.domain.model.Quote
import com.example.domain.model.extensions.roundToMinStep
import java.math.BigDecimal
import javax.inject.Inject

class QuoteMapper @Inject constructor() : Mapper<QuoteEntity, Quote> {
    override fun toDomain(entity: QuoteEntity) = Quote(
        ticker = entity.ticker.orEmpty(),
        priceChangeInPoints = entity.priceChangeInPoints,
        latestTradePrice = entity.latestTradePrice,
        exchangeOfLatestTrade = entity.exchangeOfLatestTrade.orEmpty(),
        name = entity.name.orEmpty(),
        minStep = entity.minStep,
        priceChangeByPercentage = entity.priceChangeByPercentage,
        percentageChangeText = entity.priceChangeByPercentage?.let { percent ->
            if (percent > BigDecimal.ZERO) "+${percent}%" else "${percent}%"
        }.orEmpty(),
        priceChangeInPointsText = entity.priceChangeInPoints?.roundToMinStep(entity.minStep),
        latestTradePriceText = entity.latestTradePrice?.roundToMinStep(entity.minStep)
    )

    override fun toEntity(model: Quote) = QuoteEntity()
}