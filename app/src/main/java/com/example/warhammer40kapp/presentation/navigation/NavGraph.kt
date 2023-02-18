package com.example.warhammer40kapp.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.warhammer40kapp.presentation.cards.WarHammerCardsListScreen
import com.example.warhammer40kapp.presentation.detailscreen.CardDetailScreen
import com.example.warhammer40kapp.presentation.navigation.Destinations.CARD_DETAIL_SCREEN_ARGUMENTS
import com.example.warhammer40kapp.presentation.navigation.Destinations.CARD_DETAIL_SCREEN_ROUTE_WITH_ARGUMENTS
import com.example.warhammer40kapp.presentation.navigation.Destinations.CARD_LIST_ROUTE

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = CARD_LIST_ROUTE,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = CARD_LIST_ROUTE) {
            WarHammerCardsListScreen(
                navAction = navActions
            )
        }

        composable(
            route = CARD_DETAIL_SCREEN_ROUTE_WITH_ARGUMENTS,
            arguments = CARD_DETAIL_SCREEN_ARGUMENTS
        ) { entry ->
            CardDetailScreen(
                onBack = { navController.popBackStack() },
                entry = entry
            )
        }
    }
}


