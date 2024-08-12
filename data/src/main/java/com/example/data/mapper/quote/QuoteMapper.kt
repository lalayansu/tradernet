package com.example.data.mapper.quote

import com.example.data.entity.QuoteEntity
import com.example.data.mapper.Mapper
import com.example.domain.model.Quote
import java.util.Locale
import javax.inject.Inject

class QuoteMapper @Inject constructor() : Mapper<QuoteEntity, Quote> {
    override fun toDomain(entity: QuoteEntity) = Quote(
        c = entity.c.orEmpty(),
        chg = entity.chg,
        ltp = entity.ltp,
        ltr = entity.ltr.orEmpty(),
        name = entity.name.orEmpty(),
        pcp = entity.pcp,
        percentageChangeText = entity.pcp?.let { if (it > 0) "+${it}%" else "${it}%" }.orEmpty(),
        changeText = entity.chg?.roundToMinStep(entity.min_step),
        priceText = entity.ltp?.roundToMinStep(entity.min_step)
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
                digitCount = minStep.getFractionDigits() ?: 2
            )
        }
    }
}

fun Double?.getFractionDigits() = toString().split('.')[1].toIntOrNull()

fun Double.formatWithDigits(digitCount: Int) = String.format(Locale.US, "%.${digitCount}f", this)