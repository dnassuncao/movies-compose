package br.com.dnassuncao.movieapp.features.movie_detail.data.model

import com.google.gson.annotations.SerializedName

data class GenresItem(
    @SerializedName("name") val name: String = "",
    @SerializedName("id") val id: Int = 0
)