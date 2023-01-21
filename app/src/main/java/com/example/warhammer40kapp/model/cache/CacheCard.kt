package com.example.warhammer40kapp.model.cache

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.warhammer40kapp.model.network.Card
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(
    tableName = "warhammer_table"
)
data class CacheCard(
    @PrimaryKey(autoGenerate = false)
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
    ) : Parcelable


fun List<Card>.mapToCache(): List<CacheCard> {
    return this.map { card ->
        CacheCard(
            colourText = card.colourText ?: "",
            glory = card.glory ,
            id = card.id,
            isNew = card.isNew,
            name = card.name,
            number = card.number,
            rulesText = card.rulesText ?: "",
            surge = card.surge,
            type = card.type,
            warbandId = card.warbandId,
            setId = card.setId
        )
    }
}

fun Card.mapToCache(): CacheCard {
    return this.let{ card ->
        CacheCard(
            colourText = card.colourText ?: "",
            glory = card.glory ,
            id = card.id,
            isNew = card.isNew,
            name = card.name,
            number = card.number,
            rulesText = card.rulesText ?: "",
            surge = card.surge,
            type = card.type,
            warbandId = card.warbandId,
            setId = card.setId
        )
    }
}