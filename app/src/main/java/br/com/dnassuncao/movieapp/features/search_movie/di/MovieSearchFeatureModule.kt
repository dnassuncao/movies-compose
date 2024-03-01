package br.com.dnassuncao.movieapp.features.search_movie.di

import br.com.dnassuncao.movieapp.core.data.remote.MovieService
import br.com.dnassuncao.movieapp.features.search_movie.data.repository.MovieSearchRepositoryImpl
import br.com.dnassuncao.movieapp.features.search_movie.data.source.MovieSearchRemoteDataSourceImpl
import br.com.dnassuncao.movieapp.features.search_movie.domain.repository.MovieSearchRepository
import br.com.dnassuncao.movieapp.features.search_movie.domain.source.MovieSearchRemoteDataSource
import br.com.dnassuncao.movieapp.features.search_movie.domain.usecase.GetSearchMoviesUseCase
import br.com.dnassuncao.movieapp.features.search_movie.domain.usecase.GetSearchMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieSearchFeatureModule {

    @Provides
    @Singleton
    fun provideMovieSearchRemoteDataSource(service: MovieService): MovieSearchRemoteDataSource {
        return MovieSearchRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideMovieSearchRepository(dataSource: MovieSearchRemoteDataSource): MovieSearchRepository {
        return MovieSearchRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideGetSearchMoviesUseCase(repository: MovieSearchRepository): GetSearchMoviesUseCase {
        return GetSearchMoviesUseCaseImpl(repository)
    }
}