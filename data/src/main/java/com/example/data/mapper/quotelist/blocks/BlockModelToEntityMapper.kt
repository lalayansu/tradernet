package com.example.data.mapper.quotelist.blocks

import com.example.data.entity.BlockEntity
import com.example.data.mapper.ModelToEntityMapper
import com.example.data.mapper.quotelist.item.ItemModelToEntityMapper
import com.example.data.model.response.BlockModel
import javax.inject.Inject

class BlockModelToEntityMapper @Inject constructor(
    private val itemModelToEntityMapper: ItemModelToEntityMapper
) : ModelToEntityMapper<BlockModel, BlockEntity> {

    override fun toEntity(model: BlockModel) = BlockEntity(
        color = model.color,
        group = model.group,
        icon = model.icon,
        items = model.itemModels?.map { itemModel ->
            itemModel?.let {
                itemModelToEntityMapper.toEntity(it)
            }
        },
        title = model.title,
        type = model.type,
    )
}