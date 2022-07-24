package com.team23.home.data.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.URL

suspend fun String.downloadBitmap(dispatcher: CoroutineDispatcher): Bitmap? = withContext(dispatcher) {
    runCatching {
        BitmapFactory.decodeStream(URL(this@downloadBitmap).openConnection().getInputStream())
    }
}.getOrNull()