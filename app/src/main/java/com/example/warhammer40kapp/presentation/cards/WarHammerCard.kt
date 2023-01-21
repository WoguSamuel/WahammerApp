package com.example.warhammer40kapp.presentation.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.warhammer40kapp.model.cache.CacheCard
import com.example.warhammer40kapp.ui.theme.Warhammer40KAppTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun WarHammerCard(
    card : CacheCard,
    onClick : () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp
    ) {
        Column {
            CoilImage(
                imageModel = "https://images.warhammerunderworlds.com/en/${card.id}.png",
                modifier = Modifier
                    .fillMaxSize(),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            )
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = card.name,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.fillMaxWidth().height(2.dp))
            Text(text = " Card No. ${card.number}")
            Text(text = " Type: ${card.type.replaceFirstChar {it.uppercaseChar()}}")
            Text(text = " Warband: ${card.warbandId}")
            Text(text = " Set: ${card.setId}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WarHammerCardPreview() {
    Warhammer40KAppTheme {
        WarHammerCard(
            CacheCard(
                ",",
                "",
                0,
                true,
                "",
            0,
                "",
                true,
                "",
                "",
                ""
            ),
            {}
        )
    }
}


