package com.tawuniya.userinfo.features.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tawuniya.userinfo.features.favorite.favoriteGraph
import com.tawuniya.userinfo.features.home.HOME_GRAPH_ROUTE
import com.tawuniya.userinfo.features.home.homeGraph


const val ROOT_DEEPLINK = "app://com.tawuniya.userinfo.navigation"
const val ROOT_NAV_GRAPH = "ROOT_NAV_GRAPH"

@Composable
internal fun RootNavHost(rootController: NavHostController = rememberNavController()) {
    NavHost(
        navController = rootController,
        startDestination = HOME_GRAPH_ROUTE,
        route = ROOT_NAV_GRAPH
    ) {
        homeGraph(navController = rootController)

        favoriteGraph(navController = rootController)
    }
}
