package org.kmm.sample

import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.TypeInfo
import java.awt.List
import kotlin.reflect.KClass

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    install(ContentNegotiation){
        json()
    }

    install(StatusPages) {
        exception<Throwable> { cause, type ->
            cause.respond(HttpStatusCode.InternalServerError, "Something went wrong: ${cause.response.status()?.value}")
        }
    }


    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }
        get("/greeting") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }

        get("/list") {

            call.respond(
                HttpStatusCode.OK,
                message = listOf("One", "Two", "Three", "Four", "Five", "Six")
            )

        }
    }
}