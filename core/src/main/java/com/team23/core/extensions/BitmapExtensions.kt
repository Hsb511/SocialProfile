package com.team23.core.extensions

import android.graphics.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.URL

suspend fun String.downloadBitmap(dispatcher: CoroutineDispatcher): Bitmap? = withContext(dispatcher) {
    runCatching {
        BitmapFactory.decodeStream(URL(this@downloadBitmap).openConnection().getInputStream())
    }
}.getOrNull()

fun Bitmap.toRoundBitmap(): Bitmap {
    val roundedBitmap = Bitmap.createBitmap(this.width, this.height, this.config)
    val rectF = RectF(0f, 0f, this.width.toFloat(), this.height.toFloat())
    val bitmapShader = BitmapShader(this, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    val paint = Paint().apply {
        isAntiAlias = true
        shader = bitmapShader
    }
    Canvas(roundedBitmap).drawRoundRect(rectF, 100f, 100f, paint)
    return roundedBitmap
}