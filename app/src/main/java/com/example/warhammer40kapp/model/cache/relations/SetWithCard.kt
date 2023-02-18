package com.example.warhammer40kapp.model.cache.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.warhammer40kapp.model.cache.CacheCard
import com.example.warhammer40kapp.model.cache.CacheSet
import com.example.warhammer40kapp.model.cache.CacheWarband
import com.example.warhammer40kapp.model.network.Card

data class SetWithCard(
    @Embedded val set: CacheSet,
    @Relation(
          parentColumn = "id",
          entityColumn = "setId"
    )
    val card: CacheCard
)