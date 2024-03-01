package br.com.dnassuncao.movieapp.features.movie_detail.data.model

import com.google.gson.annotations.SerializedName

data class ProductionCountriesItem(
    @SerializedName("iso_3166_1")
    val iso: String = "",
    @SerializedName("name")
    val name: String = ""
)