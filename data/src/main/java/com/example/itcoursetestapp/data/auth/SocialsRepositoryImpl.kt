package com.example.itcoursetestapp.data.auth

import com.example.itcoursetestapp.domain.auth.SocialsRepository

class SocialsRepositoryImpl(
    private val vkDataSource: VkDataSource,
    private val okDataSource: OkDataSource
) : SocialsRepository {
    override suspend fun fetchVkUrl(): String {
        return vkDataSource.fetchUrl()
    }

    override suspend fun fetchOkUrl(): String {
        return okDataSource.fetchUrl()
    }
}