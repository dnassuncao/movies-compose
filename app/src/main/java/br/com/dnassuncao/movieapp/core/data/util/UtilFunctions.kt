package br.com.dnassuncao.movieapp.core.data.util

import timber.log.Timber

class UtilFunctions {

    fun logError(tag: String, message: String) {
        Timber.tag(tag).e("Error -> $message")
    }

    fun logInfo(tag: String, message: String) {
        Timber.tag(tag).i("Info -> $message")
    }
}