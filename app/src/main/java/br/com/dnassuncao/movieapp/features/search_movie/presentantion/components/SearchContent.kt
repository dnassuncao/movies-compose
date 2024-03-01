package br.com.dnassuncao.movieapp.features.search_movie.presentantion.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import br.com.dnassuncao.movieapp.features.search_movie.domain.MovieSearch
import br.com.dnassuncao.movieapp.ui.components.ErrorScreen
import br.com.dnassuncao.movieapp.ui.components.LoadingView
import br.com.dnassuncao.movieapp.features.movie_popular.presentation.components.MovieItem
import br.com.dnassuncao.movieapp.features.search_movie.presentantion.MovieSearchEvent
import br.com.dnassuncao.movieapp.ui.theme.black

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    pagingMovies: LazyPagingItems<MovieSearch>,
    query: String,
    onSearch: (String) -> Unit,
    onEvent: (MovieSearchEvent) -> Unit,
    onDetail: (movieId: Int) -> Unit
) {

    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(black),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        SearchComponent(
            query = query,
            onSearch = {
                isLoading = true
                onSearch(it)
            },
            onSearchChangeEvent = {
                onEvent(it)
            },
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            items(pagingMovies.itemCount) { index ->
                val movie = pagingMovies[index]
                movie?.let {
                    MovieItem(
                        id = it.id,
                        voteAverage = it.voteAverage,
                        imageUrl = it.imageUrl,
                        onClick = { movieId ->
                            onDetail(movieId)
                        }
                    )
                    isLoading = false
                }
                pagingMovies.apply {
                    when {
                        isLoading -> {
                            this@LazyVerticalGrid.item(
                                span = {
                                    GridItemSpan(maxLineSpan)
                                }
                            ) {
                                LoadingView()
                            }
                        }

                        loadState.refresh is LoadState.Error -> {
                            isLoading = false
                            this@LazyVerticalGrid.item(
                                span = {
                                    GridItemSpan(maxLineSpan)
                                }
                            ) {
                                ErrorScreen(message = "Verifique sua conexão") {
                                    retry()
                                }
                            }
                        }

                        loadState.append is LoadState.Error -> {
                            isLoading = false
                            this@LazyVerticalGrid.item(
                                span = {
                                    GridItemSpan(maxLineSpan)
                                }
                            ) {
                                ErrorScreen(message = "Verifique sua conexão") {
                                    retry()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}