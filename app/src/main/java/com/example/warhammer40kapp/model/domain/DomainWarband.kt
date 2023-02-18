package com.example.warhammer40kapp.model.domain

import androidx.room.PrimaryKey
import com.example.warhammer40kapp.model.cache.CacheWarband

data class DomainWarband(
    @PrimaryKey
    val id: String,
    val isUniversal: Boolean,
    val name: String
)

fun List<CacheWarband>.mapToDomain(): List<DomainWarband> {
    return this.map { warband ->
        DomainWarband(
            id = warband.id,
            name = warband.name,
            isUniversal = warband.isUniversal,
        )
    }
}