package one.codium.wbpo.core.entity

import androidx.compose.runtime.Composable

data class ItemMenu(
    val icon: @Composable ()-> Unit,
    val id: String
)
