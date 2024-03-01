package br.com.dnassuncao.movieapp.features.movie_popular.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.dnassuncao.movieapp.features.movie_popular.domain.Movie
import kotlinx.coroutines.flow.Flow

interface MoviePopularRepository {

    fun getPopularMovies(pagingConfig: PagingConfig): Flow<PagingData<Movie>>
}