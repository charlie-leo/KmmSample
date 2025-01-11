package org.kmm.sample

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val isIos = false
    override val engin: Any = Any()
    override val ipAddress: String = ""
}

actual fun getPlatform(): Platform = JVMPlatform()