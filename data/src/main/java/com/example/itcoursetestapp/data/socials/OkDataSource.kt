package com.example.itcoursetestapp.data.socials

class OkDataSource {
    suspend fun fetchUrl(): String = OK_URL

    companion object {
        private const val OK_URL = "https://ok.ru/"
    }
}