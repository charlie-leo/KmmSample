package org.kmm.sample

import io.ktor.client.engine.darwin.Darwin
import platform.UIKit.UIDevice

class IOSPlatform() : Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val isIos = true
    override val engin: Any = Darwin

}

actual fun getPlatform(): Platform = IOSPlatform()