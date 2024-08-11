package com.example.data.mapper.quotelist.buttons

import com.example.data.entity.ButtonsEntity
import com.example.data.mapper.ModelToEntityMapper
import com.example.data.mapper.quotelist.blocks.BlockModelToEntityMapper
import com.example.data.model.response.ButtonsModel
import javax.inject.Inject

class ButtonsModelToEntityMapper @Inject constructor(
    private val blockModelToEntityMapper: BlockModelToEntityMapper
) :
    ModelToEntityMapper<ButtonsModel, ButtonsEntity> {

    override fun toEntity(model: ButtonsModel) = ButtonsEntity(
        blocks = model.blockModels?.map { blockModel ->
            blockModel?.let { blockModelToEntityMapper.toEntity(it) }
        }
    )
}