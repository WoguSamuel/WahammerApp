package com.example.warhammer40kapp.repository

import com.example.warhammer40kapp.di.IoDispatcher
import com.example.warhammer40kapp.model.cache.mapToCache
import com.example.warhammer40kapp.model.cache.relations.SetWithCard
import com.example.warhammer40kapp.model.cache.relations.SetWithCards
import com.example.warhammer40kapp.model.cache.relations.WarbandWithCards
import com.example.warhammer40kapp.model.domain.DomainCard
import com.example.warhammer40kapp.model.domain.mapToDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

interface MainRepository {


    suspend fun updateCards()

    suspend fun updateWarbands()

    suspend fun updateSets()

    suspend fun getWarbandWithCardsFromDatabase(): List<WarbandWithCards>

    suspend fun getSetWithCardsFromDatabase(): List<SetWithCards>

    suspend fun getSetWithCardFromDatabase(): SetWithCard

    fun getCardsStream(): Flow<List<DomainCard>>

    suspend fun updateFavorite(isFavorite: Boolean, id:String)

}


class MainRepositoryImpl @Inject constructor(
    private val cacheRepository: CacheRepository,
    private val networkRepository: NetworkRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MainRepository {


    override suspend fun updateCards(): Unit = withContext(ioDispatcher) {
        try {
            val response = networkRepository.getCards()
            response.body()?.let { cardResponse ->
                cacheRepository.insertCardsToDatabase(cardResponse.mapToCache())
            }
        } catch (e: Exception) {
            Timber.e("launchJob: Exception: $e Cause: ${e.cause}" )
            e.printStackTrace()
        }
    }

    override suspend fun updateWarbands(): Unit = withContext(ioDispatcher) {
        try {
            val response = networkRepository.getWarbands()
            response.body()?.let { warbandResponse ->
                cacheRepository.insertWarbandsToDatabase(warbandResponse.mapToCache())
            }
        } catch (e: Exception) {
            Timber.e("launchJob: Exception: $e Cause: ${e.cause}" )
            e.printStackTrace()
        }
    }

    override suspend fun updateSets(): Unit = withContext(ioDispatcher) {
        try {
            val response = networkRepository.getSets()
            response.body()?.let { setResponse ->
                cacheRepository.insertSetsToDatabase(setResponse.mapToCache())
            }
        } catch (e: Exception) {
            Timber.e("launchJob: Exception: $e Cause: ${e.cause}" )
            e.printStackTrace()
        }
    }

    override suspend fun getWarbandWithCardsFromDatabase(): List<WarbandWithCards> {
        return cacheRepository.getWarbandWithCardsFromDatabase()
    }

    override suspend fun getSetWithCardsFromDatabase(): List<SetWithCards> {
        return cacheRepository.getSetWithCardsFromDatabase()
    }

    override suspend fun getSetWithCardFromDatabase(): SetWithCard {
        return cacheRepository.getSetWithCardFromDatabase()
    }

//    override suspend fun getCardsStream(): Result<List<DomainCard>> = withContext(ioDispatcher) {
//        return@withContext try {
//                val response = networkRepository.getCards()
//                response.body()?.let { cardResponse ->
//                    cacheRepository.insertCardsToDatabase(cardResponse.mapToCache())
//                }
//                Success(response.body()?.mapToCache()?.mapToDomain() ?: emptyList())
//            } catch (e: Exception) {
//                Log.e("launchJob: Exception: $e", "${e.cause}")
//                e.printStackTrace()
//                Error(e)
//            }
//        }

    override fun getCardsStream(): Flow<List<DomainCard>> {
        return cacheRepository.getCardsStream().map {
            it.mapToDomain()
        }
    }

    override suspend fun updateFavorite(isFavorite: Boolean, id:String) = withContext(ioDispatcher){
        cacheRepository.updateFavorite(isFavorite, id)
    }

}