package com.example.warhammer40kapp.presentation.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.warhammer40kapp.model.cache.CacheCard
import com.example.warhammer40kapp.presentation.WarhammerViewModel
import com.example.warhammer40kapp.presentation.navigation.NavigationActions

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun WarHammerCardListScreen(
    warHammerViewModel: WarhammerViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navAction : NavigationActions
) {

    val focusManager = LocalFocusManager.current

    Scaffold(scaffoldState = scaffoldState, topBar = {
        SearchAppBar(
            query = warHammerViewModel.query.value,
            onQueryChanged = warHammerViewModel::onQueryChanged,
            onExecuteSearch = warHammerViewModel::searchCards,
            clearFocus = focusManager::clearFocus,
        )
    }) { paddingValues ->
        val cards by warHammerViewModel.cards.collectAsStateWithLifecycle()
        CardScreenContent(
            modifier = Modifier.padding(paddingValues),
            cards = cards,
            navAction = navAction
        )
    }
}

@Composable
private fun CardScreenContent(
    modifier: Modifier = Modifier,
    cards: List<CacheCard>,
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
                navAction = navAction
            )
        }
    }
}
