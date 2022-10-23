package com.hana897trx.testheroes.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.RequestManager

object Utils {
}

object Network {
    const val URL = "https://www.superheroapi.com/api/"
    const val API_KEY = "1475620536246820"
}

object GlideUtils {
    @SuppressLint("StaticFieldLeak")
    private var glide : RequestManager? = null

    fun getInstance(context : Context) : RequestManager {
        return if(glide == null) {
            glide = Glide.with(context)
            glide!!
        } else {
            glide!!
        }
    }

    fun eraseInstance() {
        glide = null
    }
}

enum class DataSource{
    Remote,
    Local
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}