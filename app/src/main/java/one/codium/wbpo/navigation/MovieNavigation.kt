package one.codium.wbpo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import one.codium.wbpo.core.entity.ItemMenu
import one.codium.wbpo.core.entity.ThemeMode
import one.codium.wbpo.feature.info.ui.InfoScreen
import one.codium.wbpo.feature.movies.ui.details.MovieDetailsScreen
import one.codium.wbpo.feature.movies.ui.list.MovieListScreen
import one.codium.wbpo.navigation.route.InfoRoute
import one.codium.wbpo.navigation.route.MovieDetailsRoute
import one.codium.wbpo.navigation.route.MovieListRoute
import one.codium.wbpo.navigation.route.SettingsRoute
import one.codium.wbpo.settings.ui.SettingsScreen

@Composable
fun MovieNavigation(
    navController: NavHostController,
    actionMenu: (List<ItemMenu>) -> Unit,
    onThemeSelected: (ThemeMode) -> Unit
) {
    NavHost(navController = navController, startDestination = MovieListRoute) {
        composable<MovieListRoute> {
            MovieListScreen(onMovieDetailsClick = { id ->
                navController.navigate(MovieDetailsRoute(id))
            })
        }
        composable<MovieDetailsRoute> { backStackEntry ->
            val movieDetailsRoute: MovieDetailsRoute = backStackEntry.toRoute()
            MovieDetailsScreen(movieDetailsRoute.id, actions = actionMenu)
        }
        composable<SettingsRoute> {
            SettingsScreen { onThemeSelected(it) }

        }
        composable<InfoRoute> {
            InfoScreen()
        }
    }
}
