package br.com.dnassuncao.movieapp.features.search_movie.presentantion

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.dnassuncao.movieapp.R
import br.com.dnassuncao.movieapp.features.search_movie.presentantion.components.SearchContent
import br.com.dnassuncao.movieapp.ui.theme.black
import br.com.dnassuncao.movieapp.ui.theme.white

@Composable
fun MovieSearchScreen(
    uiState: MovieSearchState,
    onEvent: (MovieSearchEvent) -> Unit,
    onFetch: (String) -> Unit,
    navigateToDetailMovie: (Int) -> Unit
) {

    val movies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.search_movies),
                        color = white
                    )
                },
                backgroundColor = black
            )
        }
    ) {
        SearchContent(
            paddingValues = it,
            pagingMovies = movies,
            query = uiState.query,
            onSearch = {
                onFetch(it)
            },
            onEvent = {
                onEvent(it)
            },
            onDetail = { movieId ->
                navigateToDetailMovie(movieId)
            }
        )
    }
}