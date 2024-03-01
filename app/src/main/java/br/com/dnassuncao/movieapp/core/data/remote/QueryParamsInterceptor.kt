package br.com.dnassuncao.movieapp.core.data.remote

import br.com.dnassuncao.movieapp.BuildConfig
import br.com.dnassuncao.movieapp.core.data.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class QueryParamsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(Constants.LANGUAGE_PARAM, Constants.LANGUAGE_VALUE)
            .addQueryParameter(Constants.API_KEY_PARAM, BuildConfig.MOVIE_DB_API_KEY)
            .build()

        val newRequest = request.newBuilder().url(url).build()

        return chain.proceed(newRequest)
    }
}