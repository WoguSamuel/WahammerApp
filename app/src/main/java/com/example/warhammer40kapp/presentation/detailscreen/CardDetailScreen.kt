package com.example.warhammer40kapp.presentation.detailscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.example.warhammer40kapp.model.cache.CacheCard
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun CardDetailScreen(
    onBack: () -> Boolean,
    entry: NavBackStackEntry,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    val card = entry.arguments?.getParcelable("card", CacheCard::class.java)!!

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                cardName = card.name,
                navBack = { onBack() }
            )
        }
    ) { paddingValues ->

        CardDetailScreenContent(
            modifier = Modifier.padding(paddingValues),
            card = card
        )
    }
}

@Composable
fun CardDetailScreenContent(
    modifier: Modifier = Modifier,
    card: CacheCard
) {
    val scrollState = rememberScrollState()
    val screenPadding = Modifier.padding(
        horizontal = 0.dp,
        vertical = 0.dp,
    )
    val commonModifier = modifier
        .fillMaxWidth()
        .then(screenPadding)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(commonModifier)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            CoilImage(
                imageModel = "https://images.warhammerunderworlds.com/en/${card.id}.png",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop, alignment = Alignment.Center
                )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "Card No. ${card.number}")
                    Text(text = "Warband: ${card.warbandId}")
                    Text(text = "Set: ${card.setId}")
                }
                Text(text = card.colourText)
                Text(text = "Card Rules: ${card.rulesText}")
                Text(text = "Glory: ${card.glory}")
                Text(text = "Type: ${card.type}")
            }
        }
    }
}