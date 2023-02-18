package com.example.warhammer40kapp.model.cache.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.warhammer40kapp.model.cache.CacheCard
import com.example.warhammer40kapp.model.cache.CacheWarband

data class WarbandWithCards(
    @Embedded val warband: CacheWarband,
    @Relation(
          parentColumn = "id",
          entityColumn = "setId"
    )
    val cards: List<CacheCard>
)