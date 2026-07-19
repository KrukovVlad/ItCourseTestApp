package com.example.itcoursetestapp.domain.socials

class FetchOkUrlUseCase(private val repository: SocialsRepository) {
    suspend operator fun invoke(): String {
        return repository.fetchOkUrl()
    }
}