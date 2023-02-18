package com.example.warhammer40kapp.model.domain

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.warhammer40kapp.model.cache.CacheSet
import com.example.warhammer40kapp.model.network.Set

data class DomainSet(
    @PrimaryKey
    val id: String,
    val name: String,
    val series: Int
)

fun List<CacheSet>.mapToDomain(): List<DomainSet> {
    return this.map { set ->
        DomainSet(
            id = set.id,
            name = set.name,
            series = set.series,
        )
    }
}
