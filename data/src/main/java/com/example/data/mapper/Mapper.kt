package com.example.data.mapper

interface Mapper<Entity, EntityDt> {
    fun mapToEntityDt(entity: Entity): EntityDt
    fun mapToEntity(entityDt: EntityDt): Entity
}