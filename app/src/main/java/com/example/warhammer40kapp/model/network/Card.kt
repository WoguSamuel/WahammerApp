package com.example.warhammer40kapp.model.network

data class Card(
    val colourText: String,
    val glory: Int,
    val id: String,
    val isNew: Boolean,
    val name: String,
    val number: Int,
    val rulesText: String,
    val surge: Boolean,
    val type: String,
    val warbandId: String,
    val setId: String,
    )