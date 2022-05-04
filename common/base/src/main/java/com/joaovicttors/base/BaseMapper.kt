package com.joaovicttors.base

abstract class BaseMapper <Model: BaseModel, Entity: BaseEntity> {

    abstract fun mapToDomainEntity(data: Model): Entity

    abstract fun mapFromDomainEntity(data: Entity): Model
}