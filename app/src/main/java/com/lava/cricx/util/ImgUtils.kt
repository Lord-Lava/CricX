package com.lava.cricx.util

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.lava.cricx.BuildConfig

/**
 * @param size thumb | gthumb | de
 */
fun glideUrlBuilder(imgId: String, size: String = "gthumb"): GlideUrl {
    val url = BuildConfig.SERVER_URL + "get-image?id=$imgId&p=$size"
    return GlideUrl(
        url,
        LazyHeaders.Builder()
            .addHeader("X-RapidAPI-Key", BuildConfig.API_KEY)
            .build()
    )
}