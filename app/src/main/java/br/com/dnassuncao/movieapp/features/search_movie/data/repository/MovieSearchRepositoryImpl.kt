package br.com.dnassuncao.movieapp.features.search_movie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.dnassuncao.movieapp.features.search_movie.domain.MovieSearch
import br.com.dnassuncao.movieapp.features.search_movie.domain.repository.MovieSearchRepository
import br.com.dnassuncao.movieapp.features.search_movie.domain.source.MovieSearchRemoteDataSource
import kotlinx.coroutines.flow.Flow

class MovieSearchRepositoryImpl(
    private val dataSource: MovieSearchRemoteDataSource
): MovieSearchRepository {

    override fun getSearchMovies(
        query: String,
        pagingConfig: PagingConfig
    ): Flow<PagingData<MovieSearch>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                dataSource.getSearchMoviesPagingSource(
                    query = query
                )
            }
        ).flow
    }
}