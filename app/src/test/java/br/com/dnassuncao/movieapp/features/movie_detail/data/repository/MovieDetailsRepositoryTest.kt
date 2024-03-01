package br.com.dnassuncao.movieapp.features.movie_detail.data.repository

import br.com.dnassuncao.movieapp.features.movie_detail.domain.MovieDetails
import br.com.dnassuncao.movieapp.features.movie_detail.domain.repository.MovieDetailsRepository
import br.com.dnassuncao.movieapp.features.movie_detail.domain.source.MovieDetailsRemoteDataSource
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Test
import kotlinx.coroutines.test.runTest
import org.junit.Assert

class MovieDetailsRepositoryTest {

    private val dataSource: MovieDetailsRemoteDataSource = mockk()
    private val repository: MovieDetailsRepository = MovieDetailsRepositoryImpl(dataSource)

    @Test
    fun `Fetch movie detail, when it is passed a movie id, then returns a result of MovieDetails`(): Unit =
        runTest {
            // ARRANGE
            val movieId = 123
            val expected = MovieDetails(
                id = 123,
                title = "Thor",
                genres = listOf(),
                overview = "",
                backdropPathUrl = "/image/123.png",
                releaseDate = "10/01/2015",
                voteAverage = 2.3,
                duration = 3615,
                voteCount = 2501
            )
            coEvery {
                dataSource.getMovieDetails(
                    movieId = movieId
                )
            } returns expected

            // ACT
            val actual = repository.getMovieDetails(
                movieId = movieId
            )

            // ASSERT
            Assert.assertEquals(expected, actual)
        }
}