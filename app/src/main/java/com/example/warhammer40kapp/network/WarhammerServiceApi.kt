package com.example.warhammer40kapp.network

import com.example.warhammer40kapp.model.network.CardResponse
import com.example.warhammer40kapp.model.network.SetResponse
import com.example.warhammer40kapp.model.network.WarbandResponse
import retrofit2.Response
import retrofit2.http.GET

interface WarhammerServiceApi {

    @GET(CARD_SEARCH_PATH)
    suspend fun getCards(
    ): Response<CardResponse>

    @GET(WARBANDS_SEARCH_PATH)
    suspend fun getWarbands(
    ): Response<WarbandResponse>

    @GET(SETS_SEARCH_PATH)
    suspend fun getSets(
    ): Response<SetResponse>

    companion object {
        const val BASE_URL = "https://recruitment.gawtech.dev/"

        private const val CARD_SEARCH_PATH = "cards"
        private const val WARBANDS_SEARCH_PATH = "warbands"
        private const val SETS_SEARCH_PATH = "sets"
    }
}
