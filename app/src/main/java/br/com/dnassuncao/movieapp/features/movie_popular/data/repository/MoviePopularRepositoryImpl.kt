package br.com.dnassuncao.movieapp.features.movie_popular.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.dnassuncao.movieapp.features.movie_popular.domain.Movie
import br.com.dnassuncao.movieapp.features.movie_popular.domain.repository.MoviePopularRepository
import br.com.dnassuncao.movieapp.features.movie_popular.domain.source.MoviePopularRemoteDataSource
import kotlinx.coroutines.flow.Flow

class MoviePopularRepositoryImpl(
    private val dataSource: MoviePopularRemoteDataSource
): MoviePopularRepository {
    override fun getPopularMovies(pagingConfig: PagingConfig): Flow<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                dataSource.getPopularMoviesPagingSource()
            }
        ).flow
    }
}