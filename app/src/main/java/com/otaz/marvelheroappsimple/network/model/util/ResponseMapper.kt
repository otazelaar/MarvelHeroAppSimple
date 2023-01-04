package com.otaz.marvelheroappsimple.network.model.util

interface ResponseMapper <Response, NetworkModel> {
    fun mapFromResponse(response: Response): NetworkModel

    fun mapToResponse(networkModel: NetworkModel): Response
}