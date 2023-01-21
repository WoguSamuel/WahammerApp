package com.example.warhammer40kapp.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.warhammer40kapp.model.cache.CacheCard
import com.example.warhammer40kapp.model.cache.CacheSet
import com.example.warhammer40kapp.model.cache.CacheWarband

@Database(
    entities = [CacheCard::class, CacheWarband::class, CacheSet::class],
    version = 1,
    exportSchema = false
)
abstract class WarhammerDatabase : RoomDatabase() {

    abstract fun warhammerDAO(): WarhammerDAO

}