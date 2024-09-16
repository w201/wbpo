package one.codium.wbpo.feature.movies.ui.details

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import one.codium.wbpo.core.entity.ItemMenu
import one.codium.wbpo.feature.movies.R
import one.codium.wbpo.feature.movies.ui.widgets.BasicMovieInfo
import one.codium.wbpo.feature.movies.ui.widgets.Favorite
import one.codium.wbpo.core.entity.MovieDetails

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    viewModel: MovieDetailViewModel = hiltViewModel(),
    actions: (List<ItemMenu>) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val state = viewModel.uiState.value
        state.errorMessage?.let {
            ShowError(it)
        }
        state.movie?.let {
            ShowMovieDetails(it)
            actions.invoke(getActionMenu(it.isFavorite) { viewModel.toggleFav() })
        }
        if (state.inProgress) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

private fun getActionMenu(isFav: Boolean, onClick: () -> Unit): List<ItemMenu> = listOf(
    ItemMenu("action_fav") {
        Favorite(Modifier.size(24.dp), isFav) { onClick.invoke() }
    }
)

@Composable
fun ShowMovieDetails(movie: MovieDetails) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row {
            AsyncImage(model = movie.image, contentDescription = movie.title, modifier = Modifier.width(150.dp))
            Spacer(Modifier.width(8.dp))
            Column {
                Text(
                    text = "${movie.title} (${movie.originCountry?.joinToString { it }})",
                    style = MaterialTheme.typography.titleLarge
                )
                BasicMovieInfo(movie.releaseDate, movie.voteAverage, movie.voteCount)
                movie.budget?.let { budget ->
                    Text(
                        text = stringResource(R.string.movieDetails_budget, "\$$budget"),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                movie.revenue?.let { revenue ->
                    Text(
                        text = stringResource(R.string.movieDetails_revenue, "\$$revenue"),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
        Spacer(Modifier.width(8.dp))
        Text(text = movie.overview, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun ShowError(message: String) {
    Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
}
