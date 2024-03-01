package br.com.dnassuncao.movieapp.features.movie_popular.di

import br.com.dnassuncao.movieapp.core.data.remote.MovieService
import br.com.dnassuncao.movieapp.features.movie_popular.data.repository.MoviePopularRepositoryImpl
import br.com.dnassuncao.movieapp.features.movie_popular.data.source.MoviePopularRemoteDataSourceImpl
import br.com.dnassuncao.movieapp.features.movie_popular.domain.repository.MoviePopularRepository
import br.com.dnassuncao.movieapp.features.movie_popular.domain.source.MoviePopularRemoteDataSource
import br.com.dnassuncao.movieapp.features.movie_popular.domain.usecase.GetPopularMoviesUseCase
import br.com.dnassuncao.movieapp.features.movie_popular.domain.usecase.GetPopularMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviePopularFeatureModule {

    @Provides
    @Singleton
    fun provideMoviePopularRemoteDataSource(service: MovieService): MoviePopularRemoteDataSource {
        return MoviePopularRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideMoviePopularRepository(dataSource: MoviePopularRemoteDataSource): MoviePopularRepository {
        return MoviePopularRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideGetPopularMoviesUseCase(repository: MoviePopularRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCaseImpl(repository)
    }
}