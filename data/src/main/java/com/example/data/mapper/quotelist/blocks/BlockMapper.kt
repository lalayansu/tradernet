package com.example.data.mapper.quotelist.blocks

import com.example.data.entity.BlockEntity
import com.example.data.mapper.Mapper
import com.example.data.mapper.quotelist.item.ItemMapper
import com.example.domain.model.Block
import javax.inject.Inject

class BlockMapper @Inject constructor(
    private val itemMapper: ItemMapper
) : Mapper<BlockEntity, Block> {
    override fun toDomain(entity: BlockEntity): Block {
        return Block(
            color = entity.color,
            group = entity.group,
            icon = entity.icon,
            items = entity.items?.map { itemEntity ->
                itemEntity?.let {
                    itemMapper.toDomain(it)
                }
            },
            title = entity.title,
        )
    }

    override fun toEntity(model: Block) = BlockEntity()
}