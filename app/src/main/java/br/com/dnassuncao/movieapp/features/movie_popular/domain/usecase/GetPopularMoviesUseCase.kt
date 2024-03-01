package br.com.dnassuncao.movieapp.features.movie_popular.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.dnassuncao.movieapp.features.movie_popular.domain.Movie
import br.com.dnassuncao.movieapp.features.movie_popular.domain.repository.MoviePopularRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPopularMoviesUseCase {
    operator fun invoke(): Flow<PagingData<Movie>>
}

class GetPopularMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviePopularRepository
) : GetPopularMoviesUseCase {
    override fun invoke(): Flow<PagingData<Movie>> {
        return repository.getPopularMovies(
            PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
            )
        )
    }
}