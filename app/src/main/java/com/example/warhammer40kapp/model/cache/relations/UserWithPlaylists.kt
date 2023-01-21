package com.example.warhammer40kapp.model.cache.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.warhammer40kapp.model.cache.CacheCard
import com.example.warhammer40kapp.model.cache.CacheSet

data class SetWithCards(
    @Embedded val set: CacheSet,
    @Relation(
          parentColumn = "id",
          entityColumn = "setId"
    )
    val cards: List<CacheCard>
)