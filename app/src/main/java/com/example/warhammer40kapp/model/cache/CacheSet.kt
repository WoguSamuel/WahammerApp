package com.example.warhammer40kapp.model.cache

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.warhammer40kapp.model.network.Set

@Entity(
//    foreignKeys = [ForeignKey(entity = CacheCard::class,
//        parentColumns = arrayOf("setId"),
//        childColumns = arrayOf("id"),
//        onDelete = ForeignKey.CASCADE)],
//    indices = [
//        Index(value = ["id"]),
//        Index(value = ["setId"], unique = true)
//    ]

)
data class CacheSet(
    @PrimaryKey
//    val setId: String,
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
