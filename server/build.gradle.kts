plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.serialization)
    application
}

group = "org.kmm.sample"
version = "1.0.0"
application {
    mainClass.set("org.kmm.sample.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}
repositories {
    mavenCentral()
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.kotlinx)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.status.pages)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}