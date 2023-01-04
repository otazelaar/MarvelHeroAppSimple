package com.otaz.marvelheroappsimple.domain.model.util

interface EntityMapper <Entity, DomainModel> {
    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity
}