package com.example.warhammer40kapp.presentation.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.warhammer40kapp.model.domain.DomainCard
import com.example.warhammer40kapp.ui.theme.BloodRed
import com.example.warhammer40kapp.ui.theme.Warhammer40KAppTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun WarHammerCard(
    card: DomainCard,
    onFavoriteCard: () -> Unit,
    onClick: () -> Unit
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
            Text(text = " Type: ${card.type.replaceFirstChar { it.uppercaseChar() }}")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column() {
                        Text(text = " Warband: ${card.warbandId}")
                        Text(text = " Set: ${card.setId}")
                    }
                    FavoriteButton(
                        isFavorite = card.isFavorite,
                        onFavoriteCard = { onFavoriteCard() }
                    )
            }
        }
    }
}

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = BloodRed,
    isFavorite: Boolean,
    onFavoriteCard: () -> Unit
) {
    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = { onFavoriteCard() }
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.0f
                scaleY = 1.0f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WarHammerCardPreview() {
    Warhammer40KAppTheme {
//        WarHammerCard(
//            DomainCard(
//                ",",
//                "",
//                0,
//                true,
//                "",
//            0,
//                "",
//                true,
//                "",
//                "",
//                ""
//            ),
//            {}
//        )
    }
}


