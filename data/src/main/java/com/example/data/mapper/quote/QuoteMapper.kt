package com.example.data.mapper.quote

import com.example.data.entity.QuoteEntity
import com.example.data.mapper.Mapper
import com.example.domain.model.Quote
import java.util.Locale
import javax.inject.Inject

class QuoteMapper @Inject constructor() : Mapper<QuoteEntity, Quote> {
    override fun toDomain(entity: QuoteEntity) = Quote(
        c = entity.c.orEmpty(),
        chg = entity.chg ?: 0.0,
        ltp = entity.ltp ?: 0.0,
        ltr = entity.ltr.orEmpty(),
        name = entity.name.orEmpty(),
        pcp = entity.pcp ?: 0.0,
        percentageChangeText = entity.pcp?.let { if (it > 0) "+${it}%" else "${it}%" }.orEmpty(),
        changeText = entity.chg?.roundToMinStep(entity.min_step),
        priceText = entity.ltp?.roundToMinStep(entity.min_step)
    )

    override fun toEntity(model: Quote) = QuoteEntity()
}

fun Double.roundToMinStep(minStep: Double?): String {
    return minStep?.let {
        (Math.round(this / minStep) * minStep).formatWith2Digits()
    } ?: this.formatWith2Digits()
}

fun Double.formatWith2Digits() = String.format(Locale.US, "%.2f", this)