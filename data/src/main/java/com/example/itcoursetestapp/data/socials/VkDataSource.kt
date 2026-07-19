package com.example.itcoursetestapp.data.socials

class VkDataSource {
    suspend fun fetchUrl(): String = VK_URL

    companion object {
        private const val VK_URL = "https://vk.com/"
    }
}