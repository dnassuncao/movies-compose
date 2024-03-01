package br.com.dnassuncao.movieapp.features.movie_detail.data.source

import br.com.dnassuncao.movieapp.core.data.remote.MovieService
import br.com.dnassuncao.movieapp.features.movie_popular.data.model.MovieResponse
import br.com.dnassuncao.movieapp.core.data.util.toBackdropUrl
import br.com.dnassuncao.movieapp.features.movie_detail.domain.MovieDetails
import br.com.dnassuncao.movieapp.core.pagging.MovieSimilarPagingSource
import br.com.dnassuncao.movieapp.features.movie_detail.domain.source.MovieDetailsRemoteDataSource
import javax.inject.Inject

class MovieDetailsRemoteDataSourceImpl @Inject constructor(
    private val movieService: MovieService
) : MovieDetailsRemoteDataSource {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val response = movieService.getMovie(movieId)
        val genres = response.genres.map { it.name }
        return MovieDetails(
            id = response.id,
            title = response.title,
            genres = genres,
            overview = response.overview,
            backdropPathUrl = response.backdropPath.toBackdropUrl(),
            releaseDate = response.releaseDate,
            voteAverage = response.voteAverage,
            duration = response.runtime,
            voteCount = response.voteCount

        )
    }

    override suspend fun getMoviesSimilar(page: Int, movieId: Int): MovieResponse {
        return movieService.getMoviesSimilar(
            movieId = movieId,
            page = page

        )
    }

    override fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource {
        return MovieSimilarPagingSource(
            movieId = movieId,
            movieDetailsRemoteDataSource = this

        )
    }
}