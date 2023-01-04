package com.otaz.marvelheroappsimple.network.model.responses

import com.otaz.marvelheroappsimple.network.model.SuperheroNetworkEntity
import java.io.Serializable

class SuperheroResponseNetwork(
    val data: SuperheroResponseData,
    val total: Int,
) : Serializable

class SuperheroResponseData(
    val results: List<SuperheroNetworkEntity>,
) : Serializable