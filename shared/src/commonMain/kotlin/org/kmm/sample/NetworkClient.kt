package org.kmm.sample

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


/**
 * Created by Charles Raj I on 28/12/24
 * @project KmmSample
 * @author Charles Raj
 */


object NetworkClient {
    val httpClient : HttpClient by lazy {

        HttpClient(getPlatform().engin as HttpClientEngineFactory<*>){
            install(ContentNegotiation){
                json( Json {ignoreUnknownKeys = true})
            }
            install(Logging){
                level = LogLevel.ALL
            }

            headers {
                append("Content-Type", ContentType.Application.Json.toString())

            }
        }

    }



}