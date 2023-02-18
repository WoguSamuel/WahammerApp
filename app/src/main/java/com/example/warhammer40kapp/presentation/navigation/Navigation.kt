package com.example.warhammer40kapp.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import com.example.warhammer40kapp.presentation.navigation.Destinations.CARD_DETAIL_SCREEN_ROUTE
import com.example.warhammer40kapp.presentation.navigation.Destinations.CARD_LIST_ROUTE
import com.example.warhammer40kapp.presentation.navigation.DestinationsArgs.CARD_ARG
import com.example.warhammer40kapp.utils.WarhammerCardNavType

/**
 * Screens used in [Destinations]
 */
sealed class Screens(val route: String, val arguments: List<NamedNavArgument>) {

    object CardListScreen : Screens("CardListScreen", emptyList())

    object CardDetailScreen : Screens(
        "CardDetailScreen",
        arguments = listOf(
            navArgument("card") {
                type = WarhammerCardNavType()
            }
        )
    )
}

/**
 * [DestinationsArgs] used in the [Destinations]
 */
object DestinationsArgs {
    const val CARD_ARG = "/{card}"
}

/**
 * [Destinations] used for Navigation
 */
object Destinations {
    val CARD_LIST_ROUTE = Screens.CardListScreen.route
    val CARD_DETAIL_SCREEN_ROUTE = Screens.CardDetailScreen.route
    val CARD_DETAIL_SCREEN_ROUTE_WITH_ARGUMENTS = buildString {
        append(CARD_DETAIL_SCREEN_ROUTE)
        append(CARD_ARG)
    }
    val CARD_DETAIL_SCREEN_ARGUMENTS = Screens.CardDetailScreen.arguments
}

/**
 * Models the navigation actions in the app.
 */
class NavigationActions(private val navController: NavHostController) {

    fun navigateToCardListScreen() {
        navController.navigate(CARD_LIST_ROUTE)
    }

    fun navigateToCardDetailScreen(
        cardArgument: String,
    ) {
        val route = buildString {
            append(CARD_DETAIL_SCREEN_ROUTE)
            append("/$cardArgument")
        }
        navController.navigate(route)
    }
}
