package br.com.dnassuncao.movieapp.features.movie_detail.data.model

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesItem(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("iso_639_1")
    val iso: String = "",
    @SerializedName("english_name")
    val englishName: String = ""
)