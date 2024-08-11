package com.example.data.mapper.quotelist

import com.example.data.entity.QuotesListEntity
import com.example.data.mapper.Mapper
import com.example.data.mapper.quotelist.buttons.ButtonsMapper
import com.example.domain.model.QuotesList
import javax.inject.Inject

class QuoteListMapper @Inject constructor(
    private val buttonsMapper: ButtonsMapper
) : Mapper<QuotesListEntity, QuotesList> {
    override fun toDomain(entity: QuotesListEntity) = QuotesList(
        buttons = entity.buttons?.let { buttonsMapper.toDomain(it) },
        tickers = entity.tickers,
    )

    override fun toEntity(model: QuotesList) = QuotesListEntity()
}