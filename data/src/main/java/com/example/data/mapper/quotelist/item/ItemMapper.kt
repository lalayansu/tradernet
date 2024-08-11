package com.example.data.mapper.quotelist.item

import com.example.data.entity.ItemEntity
import com.example.data.mapper.Mapper
import com.example.domain.model.Item
import javax.inject.Inject

class ItemMapper @Inject constructor() : Mapper<ItemEntity, Item> {
    override fun toDomain(entity: ItemEntity): Item {
        return Item(
            action = entity.action,
            color = entity.color,
            id = entity.id,
            text = entity.text,
            title = entity.title,
        )
    }

    override fun toEntity(model: Item) = ItemEntity()
}