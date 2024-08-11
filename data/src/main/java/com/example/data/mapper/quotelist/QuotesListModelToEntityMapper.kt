package com.example.data.mapper.quotelist

import com.example.data.entity.QuotesListEntity
import com.example.data.mapper.ModelToEntityMapper
import com.example.data.mapper.quotelist.buttons.ButtonsModelToEntityMapper
import com.example.data.model.response.QuotesListModel
import javax.inject.Inject

class QuotesListModelToEntityMapper @Inject constructor(
    private val buttonsModelToEntityMapper: ButtonsModelToEntityMapper
) : ModelToEntityMapper<QuotesListModel, QuotesListEntity> {

    override fun toEntity(model: QuotesListModel): QuotesListEntity {
        return QuotesListEntity(
            buttons = model.buttonsModel?.let {
                buttonsModelToEntityMapper.toEntity(it)
            },
            tickers = model.tickers
        )
    }
}