package br.com.dnassuncao.movieapp.core.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.dnassuncao.movieapp.features.search_movie.domain.MovieSearch
import br.com.dnassuncao.movieapp.features.search_movie.data.mapper.toMoviesSearch
import br.com.dnassuncao.movieapp.features.search_movie.domain.source.MovieSearchRemoteDataSource
import retrofit2.HttpException
import java.io.IOException

class MovieSearchPagingSource(
    private val query: String,
    private val movieSearchRemoteDataSource: MovieSearchRemoteDataSource
) : PagingSource<Int, MovieSearch>() {
    override fun getRefreshKey(state: PagingState<Int, MovieSearch>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSearch> {
        return try {
            val pageNumber = params.key ?: 1
            val response = movieSearchRemoteDataSource.getSearchMovies(
                page = pageNumber,
                query = query
            )
            val moviesResult = response.results

            LoadResult.Page(
                data = moviesResult.toMoviesSearch(),
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