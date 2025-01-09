package com.tawuniya.userinfo.features.favorite

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.tawuniya.userinfo.features.favorite.presentation.FavoriteScreen
import com.tawuniya.userinfo.features.root.ROOT_DEEPLINK


internal const val FAVORITE_ROUTE_GRAPH_DEEPLINK = "$ROOT_DEEPLINK/favorite"
internal const val FAVORITE_ROUTE = "favorite"
internal const val FAVORITE_GRAPH_ROUTE = "favorite_graph"

fun NavController.navigateToFavorite(navOptions: NavOptions? = null) {
    navigate(route = FAVORITE_GRAPH_ROUTE, navOptions = navOptions)
}

fun NavGraphBuilder.favoriteGraph(navController: NavController) {
    navigation(
        startDestination = FAVORITE_ROUTE,
        route = FAVORITE_GRAPH_ROUTE,
        deepLinks = listOf(navDeepLink { uriPattern = FAVORITE_ROUTE_GRAPH_DEEPLINK })
    ) {

        composable(route = FAVORITE_ROUTE) {
            FavoriteScreen(onBackClicked = navController::popBackStack)
        }
    }
}