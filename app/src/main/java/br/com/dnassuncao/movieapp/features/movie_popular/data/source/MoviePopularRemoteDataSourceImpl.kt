package br.com.dnassuncao.movieapp.features.movie_popular.data.source

import br.com.dnassuncao.movieapp.core.data.remote.MovieService
import br.com.dnassuncao.movieapp.features.movie_popular.data.model.MovieResponse
import br.com.dnassuncao.movieapp.core.pagging.MoviePagingSource
import br.com.dnassuncao.movieapp.features.movie_popular.domain.source.MoviePopularRemoteDataSource
import javax.inject.Inject

class MoviePopularRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MoviePopularRemoteDataSource {
    override fun getPopularMoviesPagingSource(): MoviePagingSource {
        return MoviePagingSource(this)
    }

    override suspend fun getPopularMovies(page: Int): MovieResponse {
        return service.getPopularMovies(page)
    }
}