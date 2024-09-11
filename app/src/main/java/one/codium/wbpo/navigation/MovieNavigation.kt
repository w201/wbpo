package one.codium.wbpo.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import one.codium.wbpo.feature.movies.ui.details.MovieDetailsScreen
import one.codium.wbpo.feature.movies.ui.list.MovieListScreen

@Composable
fun MovieNavigation(
    navController: NavHostController,
    actionMenu: @Composable() (RowScope.() -> Unit)
) {
    NavHost(navController = navController, startDestination = MovieListRoute) {
        composable<MovieListRoute> {
            MovieListScreen(onMovieDetailsClick = { id ->
                navController.navigate(MovieDetailsRoute(id))
            }, getActionMenu = actionMenu)
        }
        composable<MovieDetailsRoute> { backStackEntry ->
            val movieDetailsRoute: MovieDetailsRoute = backStackEntry.toRoute()
            MovieDetailsScreen(movieDetailsRoute.id)
        }
    }
}
