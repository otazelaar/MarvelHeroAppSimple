package com.otaz.marvelheroappsimple.network.model

import com.otaz.marvelheroappsimple.domain.model.SuperheroDomain
import com.otaz.marvelheroappsimple.domain.model.util.EntityMapper

class SuperheroNetworkMapper : EntityMapper<SuperheroNetworkEntity, SuperheroDomain>{

    /**
     * This function maps [SuperheroNetworkEntity] class to the [SuperheroDomain] data class
     */
    override fun mapFromEntity(entity: SuperheroNetworkEntity): SuperheroDomain {
        return SuperheroDomain(
            id = entity.id,
            name = entity.name,
            description = entity.description,
//            thumbnail = entity.thumbnail,
//            comics = entity.comics,
        )
    }

    /**
     * This function maps [SuperheroDomain] data class to the [SuperheroNetworkEntity] class
     */
    override fun mapToEntity(domainModel: SuperheroDomain): SuperheroNetworkEntity {
        return SuperheroNetworkEntity(
            id = domainModel.id,
            name = domainModel.name,
            description = domainModel.description,
//            thumbnail = domainModel.thumbnail,
//            comics = domainModel.comics,
        )
    }

    fun fromEntityList(initial: List<SuperheroNetworkEntity>): List<SuperheroDomain>{
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<SuperheroDomain>): List<SuperheroNetworkEntity>{
        return initial.map { mapToEntity(it) }
    }

}