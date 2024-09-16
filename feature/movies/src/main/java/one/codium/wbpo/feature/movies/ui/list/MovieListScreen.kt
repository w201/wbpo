package one.codium.wbpo.feature.movies.ui.list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import one.codium.wbpo.core.entity.Movie
import one.codium.wbpo.core.entity.getImagePath
import one.codium.wbpo.feature.movies.R
import one.codium.wbpo.feature.movies.ui.widgets.BasicMovieInfo
import one.codium.wbpo.feature.movies.ui.widgets.Favorite

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = hiltViewModel(),
    onMovieDetailsClick: (Int) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val items = viewModel.movieFlow.collectAsLazyPagingItems()
        if (items.loadState.append is LoadState.Error){
            ShowError(items.loadState.append as LoadState.Error)
        }
        MovieList(items, { onMovieDetailsClick.invoke(it.id) }) {
            viewModel.toggleFav(it)
        }
        viewModel.offlineNotification?.value?.consume()?.let { notification ->
            if (notification) Toast.makeText(LocalContext.current, R.string.error_inOffline, Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
private fun ShowError(error: LoadState.Error) {
    Toast.makeText(LocalContext.current, error.error.message, Toast.LENGTH_SHORT).show()
}

@Composable
fun MovieList(movies: LazyPagingItems<Movie>, onMovieClick: (Movie) -> Unit, onFavoriteClick: (Movie) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(4.dp)) {
        items(movies.itemCount, movies.itemKey { it.id }) { index ->
            val movie = movies[index]
            if (movie != null) {
                MovieItem(movie, {onMovieClick(movie)}, {onFavoriteClick(movie)})
            } else {
                CircularProgressIndicator()
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onMovieClick: (Movie) -> Unit, onFavoriteClick: (Movie) -> Unit ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .clickable { onMovieClick(movie) }
    ) {
        AsyncImage(
            modifier = Modifier.width(150.dp),
            contentScale = ContentScale.Crop,
            model = movie.getImagePath(),
            contentDescription = movie.title
        )
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = movie.title,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Favorite(Modifier.size(16.dp), movie.isFavorite) {
                    onFavoriteClick(movie)
                }
            }
            BasicMovieInfo(movie.releaseDate, movie.voteAverage, movie.voteCount)
            Text(
                text = movie.overview,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 6,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@Preview
fun MovieItemPreview() {
    MovieItem(
        Movie(
            "",
            1,
            "Hello world",
            "Something",
            3.4,
            "/vpnVM9B6NMmQpWeZvzLvDESb2QY.jpg",
            "22.03.2024",
            "Some long title for movie or else",
            3.4,
            123
        ),
        {}
    ){}
}
