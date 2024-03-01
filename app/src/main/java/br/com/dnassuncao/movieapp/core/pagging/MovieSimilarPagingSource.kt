package br.com.dnassuncao.movieapp.core.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.dnassuncao.movieapp.features.movie_popular.domain.Movie
import br.com.dnassuncao.movieapp.features.movie_detail.domain.source.MovieDetailsRemoteDataSource
import br.com.dnassuncao.movieapp.features.movie_popular.data.mapper.toMovies
import retrofit2.HttpException
import java.io.IOException

class MovieSimilarPagingSource(
    private val movieId: Int,
    private val movieDetailsRemoteDataSource: MovieDetailsRemoteDataSource
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNumber = params.key ?: 1
            val response = movieDetailsRemoteDataSource.getMoviesSimilar(
                page = pageNumber,
                movieId = movieId
            )
            val moviesResult = response.results

            LoadResult.Page(
                data = moviesResult.toMovies(),
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (moviesResult.isEmpty()) null else pageNumber + 1
            )
        } catch (ioException: IOException) {
            return LoadResult.Error(ioException)
        } catch (httpException: HttpException) {
            return LoadResult.Error(httpException)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}