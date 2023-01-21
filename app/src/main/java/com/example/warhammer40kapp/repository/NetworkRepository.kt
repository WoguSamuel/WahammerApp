package com.example.warhammer40kapp.repository

import com.example.warhammer40kapp.model.network.CardResponse
import com.example.warhammer40kapp.model.network.SetResponse
import com.example.warhammer40kapp.model.network.WarbandResponse
import com.example.warhammer40kapp.network.WarhammerServiceApi
import retrofit2.Response
import javax.inject.Inject

interface NetworkRepository {
    suspend fun getCards(): Response<CardResponse>
    suspend fun getWarbands(): Response<WarbandResponse>
    suspend fun getSets(): Response<SetResponse>
}

class NetworkRepositoryImpl @Inject constructor(
    private val warHammerServiceApi: WarhammerServiceApi
) : NetworkRepository {

    override suspend fun getCards(): Response<CardResponse> {
        return warHammerServiceApi.getCards()
    }

    override suspend fun getWarbands(): Response<WarbandResponse> {
        return warHammerServiceApi.getWarbands()
    }

    override suspend fun getSets(): Response<SetResponse> {
        return warHammerServiceApi.getSets()
    }
}
