package org.kmm.sample

import android.os.Build
import io.ktor.client.engine.okhttp.OkHttp

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val isIos = false
    override val engin: Any = OkHttp
}

actual fun getPlatform(): Platform = AndroidPlatform()