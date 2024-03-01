package br.com.dnassuncao.movieapp.features.search_movie.presentantion

sealed class MovieSearchEvent {
    data class EnteredQuery(val value: String) : MovieSearchEvent()
}
