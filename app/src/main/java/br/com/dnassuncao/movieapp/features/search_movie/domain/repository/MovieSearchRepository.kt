package br.com.dnassuncao.movieapp.features.search_movie.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.dnassuncao.movieapp.features.search_movie.domain.MovieSearch
import kotlinx.coroutines.flow.Flow

interface MovieSearchRepository {

    fun getSearchMovies(query: String, pagingConfig: PagingConfig): Flow<PagingData<MovieSearch>>
}