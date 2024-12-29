package org.kmm.sample

interface Platform {
    val name: String
    val isIos: Boolean
    val engin : Any
}

expect fun getPlatform(): Platform