package br.com.dnassuncao.movieapp.features.movie_popular.presentation

import androidx.paging.PagingData
import br.com.dnassuncao.movieapp.features.movie_popular.domain.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MoviePopularState(
    val movies: Flow<PagingData<Movie>> = emptyFlow()
)
