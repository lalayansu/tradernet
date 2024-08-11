package com.example.data.mapper.quotelist.item

import com.example.data.entity.ItemEntity
import com.example.data.mapper.ModelToEntityMapper
import com.example.data.model.response.ItemModel
import javax.inject.Inject

class ItemModelToEntityMapper @Inject constructor() :
    ModelToEntityMapper<ItemModel, ItemEntity> {

    override fun toEntity(model: ItemModel) = ItemEntity(
        action = model.action,
        color = model.color,
        id = model.id,
        text = model.text,
        title = model.title,
    )
}