package br.com.dnassuncao.movieapp.features.movie_popular.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.dnassuncao.movieapp.R
import br.com.dnassuncao.movieapp.features.movie_popular.presentation.components.MovieContent
import br.com.dnassuncao.movieapp.ui.theme.black

@Composable
fun MoviePopularScreen(
    uiState: MoviePopularState,
    navigateToDetailMovie: (Int) -> Unit
) {

    val movies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.popular_movies)
                    )
                },
                backgroundColor = black
            )
        }
    ) {
        MovieContent(
            modifier = Modifier,
            pagingMovies = movies,
            paddingValues = it,
            onClick = { movieId ->
                navigateToDetailMovie(movieId)
            }
        )
    }
}