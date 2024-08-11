package com.example.data.mapper.quotelist.buttons

import com.example.data.entity.ButtonsEntity
import com.example.data.mapper.Mapper
import com.example.data.mapper.quotelist.blocks.BlockMapper
import com.example.domain.model.Buttons
import javax.inject.Inject

class ButtonsMapper @Inject constructor(
    private val blockMapper: BlockMapper
) : Mapper<ButtonsEntity, Buttons> {
    override fun toDomain(entity: ButtonsEntity) = Buttons(
        blocks = entity.blocks?.map { blockEntity ->
            blockEntity?.let {
                blockMapper.toDomain(it)
            }
        }
    )

    override fun toEntity(model: Buttons) = ButtonsEntity()
}