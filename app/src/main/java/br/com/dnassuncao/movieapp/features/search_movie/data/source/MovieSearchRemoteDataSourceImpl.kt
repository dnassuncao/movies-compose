package br.com.dnassuncao.movieapp.features.search_movie.data.source

import br.com.dnassuncao.movieapp.core.data.remote.MovieService
import br.com.dnassuncao.movieapp.features.search_movie.data.model.SearchResponse
import br.com.dnassuncao.movieapp.core.pagging.MovieSearchPagingSource
import br.com.dnassuncao.movieapp.features.search_movie.domain.source.MovieSearchRemoteDataSource
import javax.inject.Inject

class MovieSearchRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieSearchRemoteDataSource {

    override fun getSearchMoviesPagingSource(query: String): MovieSearchPagingSource {
        return MovieSearchPagingSource(
            movieSearchRemoteDataSource = this,
            query = query
        )
    }

    override suspend fun getSearchMovies(page: Int, query: String): SearchResponse {
        return service.searchMovie(
            page = page,
            query = query
        )
    }
}