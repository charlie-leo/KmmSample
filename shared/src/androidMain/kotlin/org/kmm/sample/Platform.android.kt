package org.kmm.sample

import android.os.Build
import io.ktor.client.engine.okhttp.OkHttp

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val isIos = false
    override val engin: Any = OkHttp
    override val ipAddress: String = "http://10.0.2.2:8080"
}

actual fun getPlatform(): Platform = AndroidPlatform()