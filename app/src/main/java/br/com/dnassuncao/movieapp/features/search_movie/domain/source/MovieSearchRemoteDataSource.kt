package br.com.dnassuncao.movieapp.features.search_movie.domain.source

import br.com.dnassuncao.movieapp.features.search_movie.data.model.SearchResponse
import br.com.dnassuncao.movieapp.core.pagging.MovieSearchPagingSource

interface MovieSearchRemoteDataSource {

    fun getSearchMoviesPagingSource(query: String): MovieSearchPagingSource
    suspend fun getSearchMovies(page: Int, query: String): SearchResponse
}