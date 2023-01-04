//package com.otaz.marvelheroappsimple.network.model.responses
//
//import com.otaz.marvelheroappsimple.domain.model.SuperheroDomain
//import com.otaz.marvelheroappsimple.network.model.SuperheroNetworkEntity
//import com.otaz.marvelheroappsimple.network.model.util.ResponseMapper
//
//class SuperheroResponseMapper : ResponseMapper<SuperheroResponse, SuperheroResponseNetwork>{
//
//    /**
//     * This function maps [SuperheroResponse] data class to the [SuperheroResponseNetwork] class
//     */
//    override fun mapFromResponse(response: SuperheroResponse): SuperheroResponseNetwork {
//        return SuperheroResponseNetwork(
//            data = response.data,
//            total = response.total,
//        )
//    }
//    /**
//     * This function maps [SuperheroResponseNetwork] data class to the [SuperheroResponse] class
//     */
//    override fun mapToResponse(networkModel: SuperheroResponseNetwork): SuperheroResponse {
//        return SuperheroResponse(
//            data = networkModel.data,
//            total = networkModel.total,
//        )
//    }
//
//    fun fromResponseList(initial: List<SuperheroResponse>): List<SuperheroResponseNetwork>{
//        return initial.map { mapFromResponse(it) }
//    }
//
//    fun toResponseList(initial: List<SuperheroResponseNetwork>): List<SuperheroResponse>{
//        return initial.map { mapToResponse(it) }
//    }
//
//}