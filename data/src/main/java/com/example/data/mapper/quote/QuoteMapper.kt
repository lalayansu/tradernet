package com.example.data.mapper.quote

import com.example.data.entity.QuoteEntity
import com.example.data.mapper.Mapper
import com.example.domain.model.Quote
import java.util.Locale
import javax.inject.Inject

class QuoteMapper @Inject constructor() : Mapper<QuoteEntity, Quote> {
    override fun toDomain(entity: QuoteEntity) = Quote(
        ticker = entity.ticker.orEmpty(),
        priceChangeInPoints = entity.priceChangeInPoints,
        latestTradePrice = entity.latestTradePrice,
        exchangeOfLatestTrade = entity.exchangeOfLatestTrade.orEmpty(),
        name = entity.name.orEmpty(),
        priceChangeByPercentage = entity.priceChangeByPercentage,
        percentageChangeText = entity.priceChangeByPercentage?.let { percent ->
            if (percent > 0) "+${percent}%" else "${percent}%"
        }.orEmpty(),
        priceChangeInPointsText = entity.priceChangeInPoints?.roundToMinStep(entity.minStep),
        latestTradePriceText = entity.latestTradePrice?.roundToMinStep(entity.minStep)
    )

    override fun toEntity(model: Quote) = QuoteEntity()
}

fun Double.roundToMinStep(minStep: Double?): String? {
    if (this == 0.0) return null
    return if (minStep == 0.0 || minStep == null) {
        this.toString()
    } else {
        minStep.let {
            (Math.round(this / minStep) * minStep).formatWithDigits(
                digitCount = minStep.getFractionDigits()
            )
        }
    }
}

fun Double?.getFractionDigits() = this?.toString()?.split('.')?.get(1)?.length ?: 2

fun Double.formatWithDigits(digitCount: Int) = String.format(Locale.US, "%.${digitCount}f", this)