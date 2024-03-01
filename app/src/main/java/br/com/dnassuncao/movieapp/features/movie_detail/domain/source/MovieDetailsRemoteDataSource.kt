package br.com.dnassuncao.movieapp.features.movie_detail.domain.source

import br.com.dnassuncao.movieapp.features.movie_popular.data.model.MovieResponse
import br.com.dnassuncao.movieapp.features.movie_detail.domain.MovieDetails
import br.com.dnassuncao.movieapp.core.pagging.MovieSimilarPagingSource

interface MovieDetailsRemoteDataSource {

    suspend fun getMovieDetails(movieId: Int): MovieDetails
    suspend fun getMoviesSimilar(page: Int, movieId: Int): MovieResponse
    fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource
}
