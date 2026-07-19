package com.example.itcoursetestapp.domain.socials

class FetchVkUrlUseCase(private val repository: SocialsRepository) {
    suspend operator fun invoke(): String {
        return repository.fetchVkUrl()
    }
}