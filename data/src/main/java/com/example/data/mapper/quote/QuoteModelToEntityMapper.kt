package com.example.data.mapper.quote

import com.example.data.entity.QuoteEntity
import com.example.data.mapper.ModelToEntityMapper
import com.example.data.model.response.QuoteModel
import javax.inject.Inject

class QuoteModelToEntityMapper @Inject constructor() :
    ModelToEntityMapper<QuoteModel, QuoteEntity> {

    override fun toEntity(model: QuoteModel) = QuoteEntity(
        ticker = model.c,
        priceChangeInPoints = model.chg,
        latestTradePrice = model.ltp,
        exchangeOfLatestTrade = model.ltr,
        minStep = model.min_step,
        name = model.name,
        priceChangeByPercentage = model.pcp,
    )
}