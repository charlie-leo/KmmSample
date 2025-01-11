package org.kmm.sample

interface Platform {
    val name: String
    val isIos: Boolean
    val engin : Any
    val ipAddress : String
}

expect fun getPlatform(): Platform