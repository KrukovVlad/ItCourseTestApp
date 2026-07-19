package com.example.itcoursetestapp.presentation.core.browser

import android.content.Context
import android.content.Intent
import com.example.itcoursetestapp.domain.core.browser.BrowserOpener
import androidx.core.net.toUri

class BrowserOpenerImpl(private val context: Context) : BrowserOpener {
    override fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri()).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }
}