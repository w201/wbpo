package one.codium.wbpo.entity

import androidx.annotation.StringRes
import one.codium.wbpo.R
import one.codium.wbpo.navigation.route.InfoRoute
import one.codium.wbpo.navigation.route.MovieListRoute
import one.codium.wbpo.navigation.route.SettingsRoute

enum class NavigationItem(@StringRes val label: Int, val route: Any) {
    MOVIES(R.string.menu_movies, MovieListRoute),
    SETTINGS(R.string.menu_settings, SettingsRoute),
    INFO(R.string.menu_info, InfoRoute)
}
