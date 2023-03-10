package com.example.warhammer40kapp.model.cache

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.warhammer40kapp.model.network.Set

@Entity
data class CacheSet(
    @PrimaryKey
    val id: String,
    val name: String,
    val series: Int
)

fun List<Set>.mapToCache(): List<CacheSet> {
    return this.map { set ->
        CacheSet(
            id = set.id,
            name = set.name,
            series = set.series,
        )
    }
}
