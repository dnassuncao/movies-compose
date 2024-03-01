package br.com.dnassuncao.movieapp.features.movie_detail.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.dnassuncao.movieapp.features.movie_popular.domain.Movie
import br.com.dnassuncao.movieapp.features.movie_detail.domain.MovieDetails
import br.com.dnassuncao.movieapp.features.movie_detail.domain.repository.MovieDetailsRepository
import br.com.dnassuncao.movieapp.features.movie_detail.domain.source.MovieDetailsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val dataSource: MovieDetailsRemoteDataSource
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return dataSource.getMovieDetails(movieId)
    }
    override suspend fun getMoviesSimilar(
        movieId: Int,
        pagingConfig: PagingConfig
    ): Flow<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                dataSource.getSimilarMoviesPagingSource(movieId = movieId)
            }
        ).flow
    }
}