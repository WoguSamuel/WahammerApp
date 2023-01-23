package com.example.warhammer40kapp.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warhammer40kapp.model.cache.CacheCard
import com.example.warhammer40kapp.model.cache.mapToCache
import com.example.warhammer40kapp.repository.CacheRepository
import com.example.warhammer40kapp.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WarhammerViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val cacheRepository: CacheRepository,
) : ViewModel() {

    val query = mutableStateOf("")
    private val _cards: MutableStateFlow<List<CacheCard>> = MutableStateFlow(emptyList())
    val cards get() = _cards.asStateFlow()

    init {
        setUp()
    }

    private fun setUp() {
        runBlocking {
            val one = async { getCardsFromNetwork() }
            val two = async { getWarbandsFromNetwork() }
            val three = async { getSetsFromNetwork() }
        }
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    fun searchCards() = viewModelScope.launch {
        _cards.value = getFilteredCardsFromDB()
    }

    private suspend fun getFilteredCardsFromDB(): List<CacheCard> =
        withContext(IO) {
            return@withContext cacheRepository.getFilteredCards(
                query = query.value
            )
        }


    private fun getCardsFromNetwork() = viewModelScope.launch(IO) {
        try {
            val response = networkRepository.getCards()
            response.body()?.let { cardResponse ->
                cacheRepository.insertCardsToDatabase(cardResponse.mapToCache())
            }
            getCardsFromDB()
        } catch (e: Exception) {
            Log.e("launchJob: Exception: $e", "${e.cause}")
            e.printStackTrace()
        }
    }

    private fun getWarbandsFromNetwork() = viewModelScope.launch(IO) {
        try {
            val response = networkRepository.getWarbands()
            response.body()?.let { warbandResponse ->
                cacheRepository.insertWarbandsToDatabase(warbandResponse.mapToCache())
            }
        } catch (e: Exception) {
            Log.e("launchJob: Exception: $e", "${e.cause}")
            e.printStackTrace()
        }
    }

    private fun getSetsFromNetwork() = viewModelScope.launch(IO) {
        try {
            val response = networkRepository.getSets()
            response.body()?.let { setResponse ->
                cacheRepository.insertSetsToDatabase(setResponse.mapToCache())
            }
        } catch (e: Exception) {
            Log.e("launchJob: Exception: $e", "${e.cause}")
            e.printStackTrace()
        }
    }

    private suspend fun getCardsFromDB() = viewModelScope.launch {
        withContext(IO) {
            _cards.update {
                cacheRepository.getCardsFromDatabase()
            }
        }
    }
}
