package com.example.warhammer40kapp.repository

import com.example.warhammer40kapp.cache.WarhammerDAO
import com.example.warhammer40kapp.model.cache.CacheCard
import com.example.warhammer40kapp.model.cache.CacheSet
import com.example.warhammer40kapp.model.cache.CacheWarband
import com.example.warhammer40kapp.model.cache.relations.SetWithCards
import com.example.warhammer40kapp.model.cache.relations.WarbandWithCards
import javax.inject.Inject

interface CacheRepository {

    suspend fun insertCardsToDatabase(cards: List<CacheCard>)

    suspend fun getFilteredCards(query: String): List<CacheCard>

    suspend fun insertWarbandsToDatabase(warbands: List<CacheWarband>)

    suspend fun insertSetsToDatabase(sets: List<CacheSet>)

    suspend fun getCardsFromDatabase() : List<CacheCard>

    suspend fun getWarbandWithCardsFromDatabase() : List<WarbandWithCards>

    suspend fun getSetWithCardsFromDatabase() : List<SetWithCards>

}

class CacheRepositoryImpl @Inject constructor(
    private val warhammerDAO: WarhammerDAO
): CacheRepository{

    override suspend fun insertCardsToDatabase(cards: List<CacheCard>) {
        warhammerDAO.insertCards(cards)
    }

    override suspend fun getFilteredCards(query: String): List<CacheCard> {
        return warhammerDAO.getFilteredCards(query)
    }

    override suspend fun insertWarbandsToDatabase(warbands: List<CacheWarband>) {
        warhammerDAO.insertWarbands(warbands)
    }

    override suspend fun insertSetsToDatabase(sets: List<CacheSet>) {
        warhammerDAO.insertSets(sets)
    }

    override suspend fun getCardsFromDatabase(): List<CacheCard> {
        return warhammerDAO.getCards()
    }

    override suspend fun getWarbandWithCardsFromDatabase(): List<WarbandWithCards> {
        return warhammerDAO.getWarbandWithCards()
    }

    override suspend fun getSetWithCardsFromDatabase(): List<SetWithCards> {
        return warhammerDAO.getSetWithCards()
    }

}