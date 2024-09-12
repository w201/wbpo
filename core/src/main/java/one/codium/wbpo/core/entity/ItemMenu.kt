package one.codium.wbpo.core.entity

import androidx.annotation.Keep
import androidx.compose.runtime.Composable

data class ItemMenu(
    val id: String,
    val icon: @Composable ()-> Unit
)
