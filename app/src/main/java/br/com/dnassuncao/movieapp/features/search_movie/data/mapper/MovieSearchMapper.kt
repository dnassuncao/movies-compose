package br.com.dnassuncao.movieapp.features.search_movie.data.mapper

import br.com.dnassuncao.movieapp.features.search_movie.data.model.SearchResult
import br.com.dnassuncao.movieapp.core.data.util.toPostUrl
import br.com.dnassuncao.movieapp.features.search_movie.domain.MovieSearch

fun List<SearchResult>.toMoviesSearch() = map {
    MovieSearch(
        id = it.id,
        title = it.title ?: "",
        voteAverage = it.voteAverage,
        imageUrl = it.posterPath.toPostUrl()
    )
}