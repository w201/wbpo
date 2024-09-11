package one.codium.wbpo.feature.movies.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import one.codium.wbpo.network.entity.Movie

@Composable
fun BasicMovieInfo(releaseDate: String, voteAverage: Double, voteCount: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = releaseDate,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .weight(1f)
        )
        Icon(
            Icons.Default.Star,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp)
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = "$voteAverage ($voteCount)",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 13.sp,
        )
        Spacer(Modifier.width(4.dp))
    }
}
