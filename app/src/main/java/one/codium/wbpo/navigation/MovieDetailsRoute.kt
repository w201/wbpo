package one.codium.wbpo.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsRoute(val id: Int, val actionMenu: @Composable() (RowScope.() -> Unit))
