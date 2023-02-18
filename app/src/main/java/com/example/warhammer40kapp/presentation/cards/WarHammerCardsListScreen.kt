package com.example.warhammer40kapp.presentation.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warhammer40kapp.model.domain.DomainCard
import com.example.warhammer40kapp.presentation.WarhammerCardsViewModel
import com.example.warhammer40kapp.presentation.navigation.NavigationActions

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun WarHammerCardsListScreen(
    warHammerViewModel: WarhammerCardsViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navAction : NavigationActions
) {

    val focusManager = LocalFocusManager.current
    val uiState = warHammerViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(scaffoldState = scaffoldState, topBar = {
        SearchAppBar(
            query = uiState.value.query,
            onQueryChanged = warHammerViewModel::onQueryChanged,
            clearFocus = focusManager::clearFocus,
        )
    }) { paddingValues ->
        CardsScreenContent(
            modifier = Modifier.padding(paddingValues),
            cards = uiState.value.cards,
            navAction = navAction,
            onFavoriteCard = warHammerViewModel::onFavoriteCard
        )
    }
}

@Composable
private fun CardsScreenContent(
    modifier: Modifier = Modifier,
    cards: List<DomainCard>,
    onFavoriteCard: (isFavorite: Boolean, id:String) -> Unit,
    navAction: NavigationActions
) {
    val screenPadding = Modifier.padding(
        horizontal = 5.dp,
        vertical = 1.dp,
    )
    val commonModifier = modifier
        .fillMaxWidth()
        .then(screenPadding)
    Surface(
        modifier = commonModifier,
        color = MaterialTheme.colors.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding()
        ) {
            WarHammerCardsList(
                cards = cards,
                navAction = navAction,
                onFavoriteCard = { isFavorite, id ->
                    onFavoriteCard(isFavorite, id)
                }
            )
        }
    }
}
