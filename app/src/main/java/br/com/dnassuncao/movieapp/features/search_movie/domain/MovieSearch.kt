package br.com.dnassuncao.movieapp.features.search_movie.domain

data class MovieSearch(
    val id: Int,
    val title: String,
    val voteAverage: Double = 0.0,
    val imageUrl: String = ""
)
