package com.example.warhammer40kapp.presentation.cards

import android.graphics.drawable.Icon
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.warhammer40kapp.R
import com.example.warhammer40kapp.model.cache.CacheCard
import com.example.warhammer40kapp.model.network.Card
import com.example.warhammer40kapp.presentation.navigation.NavigationActions
import com.google.gson.Gson
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun WarHammerCardsList(
    cards: List<CacheCard>,
    navAction: NavigationActions
) {
    Box(
        modifier = Modifier.background(color = MaterialTheme.colors.surface)
    ) {
        if (cards.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                userScrollEnabled = true
            ) {
                itemsIndexed(items = cards) { _, card ->
                    WarHammerCard(
                        card = card,
                        onClick = {
                            val cardJson = Uri.encode(Gson().toJson(card))
                            navAction.navigateToCardDetailScreen(cardJson)
                        }
                    )
                }
            }
        } else {
            NoResults()
        }
    }
}


@Composable
fun NoResults() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CoilImage(
                imageModel = R.drawable.skull,
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
            )
            Text(text = "No Results")
        }
    }
}

