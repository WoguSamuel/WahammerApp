package com.example.warhammer40kapp.model.cache

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.warhammer40kapp.model.network.Set
import com.example.warhammer40kapp.model.network.Warband

@Entity
data class CacheWarband(
    @PrimaryKey
    val id: String,
    val isUniversal: Boolean,
    val name: String
)

fun List<Warband>.mapToCache(): List<CacheWarband> {
    return this.map { warband ->
        CacheWarband(
            id = warband.id,
            name = warband.name,
            isUniversal = warband.isUniversal,
        )
    }
}