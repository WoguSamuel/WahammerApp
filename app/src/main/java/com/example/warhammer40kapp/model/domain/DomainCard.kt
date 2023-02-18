package com.example.warhammer40kapp.model.domain

import com.example.warhammer40kapp.model.cache.CacheCard

data class DomainCard(
    val id: String,
    val colourText: String,
    val glory: Int,
    val isNew: Boolean,
    val name: String,
    val number: Int,
    val rulesText: String,
    val surge: Boolean,
    val type: String,
    val warbandId: String,
    val setId: String,
    var isFavorite: Boolean
)

fun List<CacheCard>.mapToDomain(): List<DomainCard> {
    return this.map { cacheCard ->
        DomainCard(
            colourText = cacheCard.colourText,
            glory = cacheCard.glory ,
            id = cacheCard.id,
            isNew = cacheCard.isNew,
            name = cacheCard.name,
            number = cacheCard.number,
            rulesText = cacheCard.rulesText,
            surge = cacheCard.surge,
            type = cacheCard.type,
            warbandId = cacheCard.warbandId,
            setId = cacheCard.setId,
            isFavorite = cacheCard.isFavorite
        )
    }
}
