package com.example.itcoursetestapp.data.auth

import android.util.Base64
import com.example.itcoursetestapp.domain.auth.session.EmailEncoder

class Base64EmailEncoderImpl : EmailEncoder {
    override fun encode(email: String): String {
        return Base64.encodeToString(email.toByteArray(), Base64.DEFAULT)
    }

    override fun decode(encoded: String): String {
        return String(Base64.decode(encoded, Base64.DEFAULT))
    }
}