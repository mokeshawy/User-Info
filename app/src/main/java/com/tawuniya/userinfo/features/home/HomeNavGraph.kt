package com.tawuniya.userinfo.features.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.tawuniya.userinfo.features.favorite.navigateToFavorite
import com.tawuniya.userinfo.features.home.presentation.HomeScreen
import com.tawuniya.userinfo.features.root.ROOT_DEEPLINK


internal const val HOME_ROUTE_GRAPH_DEEPLINK = "$ROOT_DEEPLINK/home"
internal const val HOME_ROUTE = "home"
internal const val HOME_GRAPH_ROUTE = "home_graph"


fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(
        startDestination = HOME_ROUTE,
        route = HOME_GRAPH_ROUTE,
        deepLinks = listOf(navDeepLink { uriPattern = HOME_ROUTE_GRAPH_DEEPLINK })
    ) {

        composable(route = HOME_ROUTE) {
            HomeScreen(onNavigateToFavorite = navController::navigateToFavorite)
        }
    }
}