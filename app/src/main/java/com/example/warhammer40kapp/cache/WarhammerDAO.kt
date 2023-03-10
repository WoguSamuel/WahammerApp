package com.example.warhammer40kapp.cache

import androidx.room.*
import com.example.warhammer40kapp.model.cache.CacheCard
import com.example.warhammer40kapp.model.cache.CacheSet
import com.example.warhammer40kapp.model.cache.CacheWarband
import com.example.warhammer40kapp.model.cache.relations.SetWithCard
import com.example.warhammer40kapp.model.cache.relations.SetWithCards
import com.example.warhammer40kapp.model.cache.relations.WarbandWithCards
import kotlinx.coroutines.flow.Flow

@Dao
interface WarhammerDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCards(cards: List<CacheCard>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWarbands(warbands: List<CacheWarband>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSets(sets: List<CacheSet>)

    @Query("SELECT * FROM warhammer_table")
    fun getCards(): List<CacheCard>

    @Query("SELECT * FROM warhammer_table")
    fun observeCards(): Flow<List<CacheCard>>

    @Query("UPDATE warhammer_table SET isFavorite=:isFavorite WHERE id LIKE :id")
    fun updateFavorite(isFavorite: Boolean, id: String)

    @Query("SELECT * FROM warhammer_table WHERE name OR colourText OR rulesText LIKE '%'||:query||'%'")
    fun getFilteredCards(query: String): List<CacheCard>

    @Transaction
    @Query("SELECT * FROM CacheWarband")
    fun getWarbandWithCards(): List<WarbandWithCards>

    @Transaction
    @Query("SELECT * FROM CacheSet")
    fun getSetWithCards(): List<SetWithCards>

    @Transaction
    @Query("SELECT * FROM CacheSet")
    fun getSetWithCard(): SetWithCard

}