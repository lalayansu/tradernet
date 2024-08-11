package com.example.data.mapper

interface Mapper<Entity, Model> {

    fun toDomain(entity: Entity): Model

    fun toEntity(model: Model): Entity
}

interface ModelToEntityMapper<in Model, out Entity> {
    fun toEntity(model: Model): Entity
}