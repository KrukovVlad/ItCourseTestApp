package com.example.itcoursetestapp.domain.auth.session

interface EmailEncoder {
    fun encode(email: String): String
    fun decode(encoded: String): String
}
